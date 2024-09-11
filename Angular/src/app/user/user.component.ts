import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { catchError, map } from 'rxjs/operators';
import { CommonModule } from '@angular/common'; 
import { HttpClientModule } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { NavbarComponent } from '../navbar/navbar.component';
import { NotificationService } from '../notification.service';
import { Router } from '@angular/router';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';
import { FormsModule } from '@angular/forms';

interface JobApplication {
  id: number;
  company_name: string;
  type: string;
  role: string;
  status: string;
  location: string;
  package_lpa: string;
  discreption: string; 
  no_rounds: string;
  apply_before: string;
  isFavorite?:boolean;
}

interface ApplicationStatus {
  status: string;
  stage: string;
  pass_next: string; 
}

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, HttpClientModule, NavbarComponent, LoadingSpinnerComponent, FormsModule],
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  private apiUrl = 'http://localhost:8081/api/job-applications';
  private studentIdApiUrl = 'http://localhost:8081/api/students/id-by-email';
  private applyUrl = 'http://localhost:8081/api/applications/apply';
  private applicationsByStudentUrl = 'http://localhost:8081/api/applications/student';
private favoriteUrl = 'http://localhost:8081/api/students'


  jobApplications: JobApplication[] = [];
  filteredJobApplications: JobApplication[] = [];
  searchTerm: string = ''; 
  email: string | null = null;
  studentId: number | null = null;
  applicationStatuses: { [key: number]: ApplicationStatus | null } = {};
  notifications: { job: JobApplication; message: string }[] = [];
  filterOption: string = '';
  showModal = false;
  selectedJob: JobApplication | null = null;
  
  stages: string[] = ['WrittenTest', 'Technical Interview 1', 'Technical Interview 2', 'HR Round', 'Job Offer'];
  currentStageIndex: number = 0;

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private notificationService: NotificationService,
    private router: Router
  ) {}
  
  isLoading: boolean = true;
  
  ngOnInit(): void {
    this.loadJobApplications();
    this.loadStudentId();
    setTimeout(() => {
      this.isLoading = false;
    }, 1000); 
  }

  loadJobApplications(): void {
    this.getJobApplications().pipe(
      catchError(error => {
        console.error('Error fetching job applications:', error);
        return of([]); 
      })
    ).subscribe(data => {
      this.jobApplications = data;
      this.filteredJobApplications = data; 
      this.checkApplicationStatuses();
      if(this.studentId)
      this.checkFavorites(); 
    });
  }
  toggleFavorite(job: JobApplication): void {
    if (this.studentId) {
      if (job.isFavorite) {
       
        this.http.delete(`${this.favoriteUrl}/${this.studentId}/delete-fav-job/${job.id}`).subscribe(
          () => {
            job.isFavorite = false;
            console.log('Job removed from favorites');
          },
          error => {
            console.error('Error removing job from favorites', error);
          }
        );
      } else {
        // Add to favorites
        this.http.post(`${this.favoriteUrl}/${this.studentId}/add-jobs/${job.id}`, {}).subscribe(
          () => {
            job.isFavorite = true;
            console.log('Job added to favorites');
           
          },
          error => {
            console.error('Error adding job to favorites', error);
          }
        );
      }
    } 
  }

  checkFavorites(): void {
    this.jobApplications.forEach(job => {
      this.http.get<boolean>(`${this.favoriteUrl}/isFavorite/${this.studentId}/${job.id}`, {
        
      }).subscribe(isFavorite => {
        job.isFavorite = isFavorite;
      });
    });
  }


  loadStudentId(): void {
    this.route.queryParams.subscribe(params => {
      this.email = params['email'];
      if (this.email) {
        this.getStudentIdByEmail(this.email).pipe(
          catchError(error => {
            console.error('Error fetching student ID:', error);
            return of(null); 
          }),
          map(id => {
            if (id !== null) {
              this.studentId = id;
              this.checkApplicationStatuses();
              this.checkFavorites();
            } else {
              console.error('Student ID not found for email:', this.email);
            }
          })
        ).subscribe();
      }
    });
  }

  getJobApplications() {
    return this.http.get<JobApplication[]>(this.apiUrl);
  }

  getStudentIdByEmail(email: string) {
    return this.http.get<number>(`${this.studentIdApiUrl}?email=${email}`);
  }

  getApplicationsByStudentId(studentId: number) {
    return this.http.get<any[]>(`${this.applicationsByStudentUrl}/${studentId}`);
  }

  checkApplicationStatuses(): void {
    if (this.studentId) {
      this.getApplicationsByStudentId(this.studentId).pipe(
        catchError(error => {
          console.error('Error fetching student applications:', error);
          return of([]); 
        })
      ).subscribe(applications => {
        this.applicationStatuses = {};
        const newNotifications: { job: JobApplication; message: string }[] = [];

        applications.forEach(app => {
          const jobId = app.jobApplication.id;
          const newStatus = app.status;
          const newStage = app.stage;
          const passNext = app.pass_next; 

          if (!this.applicationStatuses[jobId]) {
            this.applicationStatuses[jobId] = { status: newStatus, stage: newStage, pass_next: passNext };
            newNotifications.push({
              job: app.jobApplication,
              message: `Applied for ${app.jobApplication.company_name}. Status: ${newStatus}, Stage: ${newStage}`
            });
          } else {
            const currentApplicationStatus = this.applicationStatuses[jobId];

            if (currentApplicationStatus) {
              const oldStatus = currentApplicationStatus.status;
              const oldStage = currentApplicationStatus.stage;

              if (newStatus !== oldStatus || newStage !== oldStage) {
                newNotifications.push({
                  job: app.jobApplication,
                  message: `Updated status for ${app.jobApplication.company_name}. Status: ${newStatus}, Stage: ${newStage}`
                });

                this.applicationStatuses[jobId] = {
                  ...currentApplicationStatus,
                  status: newStatus,
                  stage: newStage,
                  pass_next: passNext 
                };
              }
            }
          }
        });

        this.notificationService.setNewNotifications(newNotifications);
      });
    }
  }

  apply(job: JobApplication): void {
    if (this.studentId) {
      this.http.post(this.applyUrl, {
        student: { id: this.studentId },
        jobApplication: { id: job.id },
        status: 'PENDING',
        stage: 'Applied',
        is_stage: 0,
        pass_next: 'TRUE' 
      }).pipe(
        catchError(error => {
          console.error('Error applying for job:', error);
          return of(null);
        })
      ).subscribe(response => {
        if (response) {
          this.checkApplicationStatuses(); 
          this.toastr.success('Your application was submitted successfully!', 'Application Submitted');
        } else {
          console.error('Failed to submit application');
        }
      });
    } else {
      console.error('Student ID is not available');
    }
  }

  viewDetails(job: JobApplication): void {
    this.selectedJob = job;
    this.currentStageIndex = this.stages.indexOf(this.applicationStatuses[job.id]?.stage || 'WrittenTest');
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
  }

  hasApplied(jobId: number): boolean {
    return !!this.applicationStatuses[jobId];
  }

  logout(): void {
    localStorage.removeItem('sessionUser');  
    this.router.navigate(['/login']);   
    this.toastr.info('You have been logged out.');
  }

  searchJobs(): void {
    if (this.searchTerm.trim()) {
        this.filteredJobApplications = this.jobApplications.filter(job =>
            job.company_name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
            job.role.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
            job.location.toLowerCase().includes(this.searchTerm.toLowerCase())
        );
    } else {
        this.filteredJobApplications = [...this.jobApplications]; 
    }
    this.applyFilter();
  }

  getPassNext(jobId: number): string | undefined {
    const passNext = this.applicationStatuses[jobId]?.pass_next;
    return passNext;
  }
  applyFilter(): void {
    this.filteredJobApplications = this.jobApplications.filter(job => {
      let matchesFilter: boolean = true;
  
      if (this.filterOption === 'favorite') {
        matchesFilter = job.isFavorite === true;  
        matchesFilter = this.applicationStatuses[job.id]?.status === 'ACCEPTED';
      } else if (this.filterOption === 'rejected') {
        matchesFilter = this.applicationStatuses[job.id]?.status === 'REJECTED';
      } else if (this.filterOption === 'pending') {
        matchesFilter = this.applicationStatuses[job.id]?.status === 'PENDING';
      } else if (this.filterOption === 'open') {
        matchesFilter = job.status === 'Open';
      } else if (this.filterOption === 'closed') {
        matchesFilter = job.status === 'Closed';
      } else if (this.filterOption === 'offer') {
        matchesFilter = this.applicationStatuses[job.id]?.stage === 'Job Offer';
      }
      else if (this.filterOption === 'WrittenTest') {
        matchesFilter = this.applicationStatuses[job.id]?.stage === 'WrittenTest';
      }
      else if (this.filterOption === 'Technical Interview 1') {
        matchesFilter = this.applicationStatuses[job.id]?.stage === 'Technical Interview 1';
      }
      else if (this.filterOption === 'Technical Interview 2') {
        matchesFilter = this.applicationStatuses[job.id]?.stage === 'Technical Interview 2';
      } else if (this.filterOption === 'HR Round') {
        matchesFilter = this.applicationStatuses[job.id]?.stage === 'HR Round';
      }
  
      const matchesSearch = this.searchTerm.trim().length === 0 ||
        job.company_name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        job.role.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        job.location.toLowerCase().includes(this.searchTerm.toLowerCase());
  
      return matchesFilter && matchesSearch;
    });
  }
  
  


}
