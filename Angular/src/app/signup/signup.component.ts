import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ToastrModule } from 'ngx-toastr';
@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  studentObj: Student;
  selectedImage: File | null = null;
  selectedPdf: File | null = null;
  imageUrl: string | ArrayBuffer | null = '../assets/man-avatar.png';
  pdfUrl: SafeResourceUrl | null = null;
  apiUrl = 'http://localhost:8081/api'; 

  constructor(private http: HttpClient, private sanitizer: DomSanitizer,  private router: Router,
    private toastr: ToastrService,) {
    this.studentObj = new Student();
  }

  onSignup() {
    const signupUrl = `${this.apiUrl}/students/signup`; 
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    this.http.post(signupUrl, this.studentObj, { headers, responseType: 'text' }).subscribe(
      (response: any) => {
        console.log('Sign-up successful:', response);
        this.upload(); 
        this.toastr.success('Sign-Up Successful');
        this.router.navigate(['/login']);
      },
      (error: any) => {
        console.error('Sign-up failed:', error);
        this.toastr.error('Sign-Up failed');
      }
    );
  }

  onImageSelected(event: any): void {
    this.selectedImage = event.target.files[0];
  }

  onPdfSelected(event: any): void {
    this.selectedPdf = event.target.files[0];
  }

  onProfilePictureSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imageUrl = e.target.result; 
      };
      reader.readAsDataURL(file);
      this.selectedImage = file;
    }
  }

  onResumeSelected(event: any): void {
    this.selectedPdf = event.target.files[0];
  }

  upload(): void {
    const formData = new FormData();

    
    if (!this.selectedImage) {
      fetch('../assets/man-avatar.png')
        .then(response => response.blob())
        .then(blob => {
          const defaultImageFile = new File([blob], 'man-avatar.png', { type: 'image/png' });
          formData.append('image', defaultImageFile); 
          this.uploadFormData(formData); 
        })
        .catch(error => {
          console.error('Error fetching default image:', error);
        });
    } else {
      formData.append('image', this.selectedImage); 
    }

    if (this.selectedPdf) {
      formData.append('pdf', this.selectedPdf);
    }

    formData.append('email', this.studentObj.email);

    if (this.selectedImage) {
      this.uploadFormData(formData);
    }
  }

  uploadFormData(formData: FormData): void {
    this.http.post(`${this.apiUrl}/upload`, formData, { responseType: 'text' }).subscribe(
      response => {
        console.log('Upload successful:', response);
        this.selectedImage = null;
        this.selectedPdf = null;
        this.imageUrl = '../assets/man-avatar.png';
      },
      error => {
        console.error('Upload failed:', error);
      }
    );
  }
}

export class Student {
  firstName: string;
  lastName: string;
  password: string;
  gender: string;
  dob: string;  
  phoneNumber: string;
  email: string;
  mark10th: number;
  mark12th: number;
  ugMark: number;
  backlog: number;
  activeBacklog: number;
  interestedCourse: string;

  constructor() {
    this.firstName = '';
    this.lastName = '';
    this.password = '';
    this.gender = '';
    this.dob = '';
    this.phoneNumber = '';
    this.email = '';
    this.mark10th = 0;
    this.mark12th = 0;
    this.ugMark = 0;
    this.backlog = 0;
    this.activeBacklog = 0;
    this.interestedCourse = '';
  }
}
