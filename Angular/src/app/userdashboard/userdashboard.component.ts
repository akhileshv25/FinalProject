import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';  // Import FormsModule
import { NavbarComponent } from "../navbar/navbar.component";
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { LoadingSpinnerComponent } from '../loading-spinner/loading-spinner.component';

interface Application {
  status: string;
  // Add other properties if needed
}

@Component({
  selector: 'app-userdashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule, FormsModule, NavbarComponent,LoadingSpinnerComponent],
  templateUrl: './userdashboard.component.html',
  styleUrls: ['./userdashboard.component.css']
})
export class UserdashboardComponent implements OnInit {
  student: any;
  loading = true;
  error: string | null = null;
  appliedCount: number = 0;
  acceptedCount: number = 0;
  rejectedCount: number = 0;
  pendingCount: number = 0;
  isEditModalOpen: boolean = false;
  pdfUrl: SafeResourceUrl | null = null;
  isPopupOpen: boolean = false;
  apiUrl = 'http://localhost:8081/api';
  email: string | null = null;
  private imageApiUrl = 'http://localhost:8081/api/download/image'; // Adjust URL as needed
  isAdmin = false;
  isUser = false;
  imageUrl: string | ArrayBuffer | null = null;
  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private toast: ToastrService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.email = params['email'] || localStorage.getItem('sessionUser');
  
      if (this.email) {
        setTimeout(() => {
          this.loading = false;
        }, 1000);
        this.fetchStudentData(this.email);
        this.fetchApplicationCounts(this.email);
  
        // Introduce a delay of 1 second before setting loading to false
        // 1000 ms = 1 second
      } else {
        this.error = 'Email parameter is missing';
        this.loading = true;
      }
    });
  }
  

  fetchStudentData(email: string): void {
    const url = `http://localhost:8081/api/students/email/${email}`;
    this.http.get<any>(url).subscribe({
      next: (data) => {
        this.student = data;
      //  this.loading = false;
        this.viewImage();
      },
      error: (err) => {
        console.error('Error fetching student data', err);
        this.error = 'Failed to fetch student data';
        this.loading = true;
      }
    });
  }

  fetchApplicationCounts(email: string): void {
    const url = `http://localhost:8081/api/applications/student/email/${email}`;
    this.http.get<any[]>(url).subscribe({
      next: (data) => {
        this.acceptedCount = data.filter(app => app.status === 'ACCEPTED').length;
        this.rejectedCount = data.filter(app => app.status === 'REJECTED').length;
        this.pendingCount = data.filter(app => app.status === 'PENDING').length;
        this.appliedCount = this.acceptedCount + this.rejectedCount + this.pendingCount;
      },
      error: (err) => {
        console.error('Error fetching application counts', err);
        this.error = 'Failed to fetch application counts';
      }
    });
  }

  openEditModal(): void {
    this.isEditModalOpen = true;
  }

  closeEditModal(): void {
    this.isEditModalOpen = false;
  }

  saveStudent(): void {
    const url = `http://localhost:8081/api/students/${this.student.id}`;
    this.http.put(url, this.student).subscribe({
      next: () => {
        this.fetchStudentData(this.student.email); 
        this.closeEditModal();
        this.toast.success('Data Updated', 'Successfully!!');
      },
      error: (err) => {
        console.error('Error updating student data', err);
        this.error = 'Failed to update student data';
      }
    });
  }

  viewPdf(): void {
    if (this.email) {
      this.http.get(`${this.apiUrl}/download/pdf/${this.email}`, { responseType: 'blob' }).subscribe(
        (response: Blob) => {
          console.log('Response Blob:', response);  // Log the raw Blob
          const pdfBlobUrl = URL.createObjectURL(response);
          console.log('Blob URL:', pdfBlobUrl);  // Log the Blob URL
          this.pdfUrl = this.sanitizer.bypassSecurityTrustResourceUrl(pdfBlobUrl);
          console.log('Sanitized PDF URL:', this.pdfUrl);  // Log the sanitized URL
          this.isPopupOpen = true; // Open the modal
        },
        error => {
          console.error('Failed to load PDF', error);
        }
      );
    }
  }
  
  closePopup(): void {
    this.isPopupOpen = false; // Close the modal
  }

  viewImage(): void {
    const email = localStorage.getItem('sessionUser');
    if (email) {
      this.http.get(`${this.imageApiUrl}/${email}`, { responseType: 'blob' }).subscribe(
        (response: Blob) => {
          console.log('Image Response:', response);
          console.log('Image MIME Type:', response.type); // Log MIME type
  
          if (response.type.startsWith('image/')) { // Check if the response is an image
            const reader = new FileReader();
            reader.onload = () => {
              this.imageUrl = reader.result;
              console.log('Image URL:', this.imageUrl); // Log the image URL
            };
            reader.readAsDataURL(response);
          } else {
            console.error('Response is not an image');
          }
        },
        error => {
          console.error('Failed to load image', error);
        }
      );
    }
  }
  
}
