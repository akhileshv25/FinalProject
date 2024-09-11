package com.drive.student.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobApplicationDBInit implements CommandLineRunner {

    @Autowired
    private JobApplicationRepo jobApplicationRepo;

    @Override
    public void run(String... args) throws Exception {
        if (jobApplicationRepo.count() > 0) {
            log.info("Job Application data already exists");
            return;
        }

        try {
            // Create Job Application 1
            JobApplication jobApplication1 = new JobApplication();
            jobApplication1.setCompany_name("Apple");
            jobApplication1.setType("Full-Time");
            jobApplication1.setRole("Software Engineer");
            jobApplication1.setStatus("Open");
            jobApplication1.setLocation("Cupertino, CA");
            jobApplication1.setPackage_lpa("20");
            jobApplication1.setDiscreption("We are looking for a software engineer to work on iOS development.");
            jobApplication1.setNo_rounds("4");
            jobApplication1.setApply_before("2024-09-30");

            jobApplicationRepo.save(jobApplication1);

            // Create Job Application 2
            JobApplication jobApplication2 = new JobApplication();
            jobApplication2.setCompany_name("Microsoft");
            jobApplication2.setType("Full-Time");
            jobApplication2.setRole("Cloud Engineer");
            jobApplication2.setStatus("Open");
            jobApplication2.setLocation("Redmond, WA");
            jobApplication2.setPackage_lpa("22");
            jobApplication2.setDiscreption("Looking for a cloud engineer to work on Azure solutions.");
            jobApplication2.setNo_rounds("3");
            jobApplication2.setApply_before("2024-10-15");

            jobApplicationRepo.save(jobApplication2);

            // Create Job Application 3
            JobApplication jobApplication3 = new JobApplication();
            jobApplication3.setCompany_name("Google");
            jobApplication3.setType("Full-Time");
            jobApplication3.setRole("Data Scientist");
            jobApplication3.setStatus("Open");
            jobApplication3.setLocation("Mountain View, CA");
            jobApplication3.setPackage_lpa("25");
            jobApplication3.setDiscreption("Hiring a data scientist to work on Google Search.");
            jobApplication3.setNo_rounds("4");
            jobApplication3.setApply_before("2024-11-01");

            jobApplicationRepo.save(jobApplication3);

            // Create Job Application 4
            JobApplication jobApplication4 = new JobApplication();
            jobApplication4.setCompany_name("Facebook");
            jobApplication4.setType("Full-Time");
            jobApplication4.setRole("Machine Learning Engineer");
            jobApplication4.setStatus("Open");
            jobApplication4.setLocation("Menlo Park, CA");
            jobApplication4.setPackage_lpa("24");
            jobApplication4.setDiscreption("Seeking a machine learning engineer to improve our AI systems.");
            jobApplication4.setNo_rounds("5");
            jobApplication4.setApply_before("2024-12-01");

            jobApplicationRepo.save(jobApplication4);

            // Create Job Application 5
            JobApplication jobApplication5 = new JobApplication();
            jobApplication5.setCompany_name("Amazon");
            jobApplication5.setType("Full-Time");
            jobApplication5.setRole("DevOps Engineer");
            jobApplication5.setStatus("Open");
            jobApplication5.setLocation("Seattle, WA");
            jobApplication5.setPackage_lpa("21");
            jobApplication5.setDiscreption("We are looking for a DevOps engineer to support AWS.");
            jobApplication5.setNo_rounds("3");
            jobApplication5.setApply_before("2024-09-30");

            jobApplicationRepo.save(jobApplication5);

            // Create Job Application 6
            JobApplication jobApplication6 = new JobApplication();
            jobApplication6.setCompany_name("Tesla");
            jobApplication6.setType("Full-Time");
            jobApplication6.setRole("Mechanical Engineer");
            jobApplication6.setStatus("Open");
            jobApplication6.setLocation("Palo Alto, CA");
            jobApplication6.setPackage_lpa("18");
            jobApplication6.setDiscreption("Seeking a mechanical engineer for automotive projects.");
            jobApplication6.setNo_rounds("3");
            jobApplication6.setApply_before("2024-10-01");

            jobApplicationRepo.save(jobApplication6);

            // Create Job Application 7
            JobApplication jobApplication7 = new JobApplication();
            jobApplication7.setCompany_name("Netflix");
            jobApplication7.setType("Full-Time");
            jobApplication7.setRole("Content Analyst");
            jobApplication7.setStatus("Open");
            jobApplication7.setLocation("Los Gatos, CA");
            jobApplication7.setPackage_lpa("15");
            jobApplication7.setDiscreption("Looking for a content analyst to work on media projects.");
            jobApplication7.setNo_rounds("2");
            jobApplication7.setApply_before("2024-09-15");

            jobApplicationRepo.save(jobApplication7);

            // Create Job Application 8
            JobApplication jobApplication8 = new JobApplication();
            jobApplication8.setCompany_name("Intel");
            jobApplication8.setType("Full-Time");
            jobApplication8.setRole("Chip Designer");
            jobApplication8.setStatus("Open");
            jobApplication8.setLocation("Santa Clara, CA");
            jobApplication8.setPackage_lpa("23");
            jobApplication8.setDiscreption("Hiring a chip designer for cutting-edge semiconductor projects.");
            jobApplication8.setNo_rounds("4");
            jobApplication8.setApply_before("2024-11-15");

            jobApplicationRepo.save(jobApplication8);

            // Create Job Application 9
            JobApplication jobApplication9 = new JobApplication();
            jobApplication9.setCompany_name("IBM");
            jobApplication9.setType("Full-Time");
            jobApplication9.setRole("Blockchain Developer");
            jobApplication9.setStatus("Open");
            jobApplication9.setLocation("Armonk, NY");
            jobApplication9.setPackage_lpa("19");
            jobApplication9.setDiscreption("We are looking for a blockchain developer to work on enterprise solutions.");
            jobApplication9.setNo_rounds("3");
            jobApplication9.setApply_before("2024-10-20");

            jobApplicationRepo.save(jobApplication9);

            // Create Job Application 10
            JobApplication jobApplication10 = new JobApplication();
            jobApplication10.setCompany_name("Adobe");
            jobApplication10.setType("Full-Time");
            jobApplication10.setRole("UI/UX Designer");
            jobApplication10.setStatus("Open");
            jobApplication10.setLocation("San Jose, CA");
            jobApplication10.setPackage_lpa("16");
            jobApplication10.setDiscreption("Hiring a UI/UX designer for creative software development.");
            jobApplication10.setNo_rounds("3");
            jobApplication10.setApply_before("2024-09-30");

            jobApplicationRepo.save(jobApplication10);

            // Create Job Application 11
            JobApplication jobApplication11 = new JobApplication();
            jobApplication11.setCompany_name("Salesforce");
            jobApplication11.setType("Full-Time");
            jobApplication11.setRole("CRM Developer");
            jobApplication11.setStatus("Open");
            jobApplication11.setLocation("San Francisco, CA");
            jobApplication11.setPackage_lpa("20");
            jobApplication11.setDiscreption("Looking for a CRM developer to expand Salesforce solutions.");
            jobApplication11.setNo_rounds("4");
            jobApplication11.setApply_before("2024-12-15");

            jobApplicationRepo.save(jobApplication11);

            // Create Job Application 12
            JobApplication jobApplication12 = new JobApplication();
            jobApplication12.setCompany_name("Oracle");
            jobApplication12.setType("Full-Time");
            jobApplication12.setRole("Database Administrator");
            jobApplication12.setStatus("Open");
            jobApplication12.setLocation("Austin, TX");
            jobApplication12.setPackage_lpa("18");
            jobApplication12.setDiscreption("Seeking a database administrator for Oracle cloud solutions.");
            jobApplication12.setNo_rounds("3");
            jobApplication12.setApply_before("2024-11-05");

            jobApplicationRepo.save(jobApplication12);

            // Create Job Application 13
            JobApplication jobApplication13 = new JobApplication();
            jobApplication13.setCompany_name("Spotify");
            jobApplication13.setType("Full-Time");
            jobApplication13.setRole("Data Engineer");
            jobApplication13.setStatus("Open");
            jobApplication13.setLocation("Stockholm, Sweden");
            jobApplication13.setPackage_lpa("17");
            jobApplication13.setDiscreption("Hiring a data engineer to work on our music recommendation algorithms.");
            jobApplication13.setNo_rounds("3");
            jobApplication13.setApply_before("2024-12-01");

            jobApplicationRepo.save(jobApplication13);

            // Create Job Application 14
            JobApplication jobApplication14 = new JobApplication();
            jobApplication14.setCompany_name("Airbnb");
            jobApplication14.setType("Full-Time");
            jobApplication14.setRole("Product Manager");
            jobApplication14.setStatus("Open");
            jobApplication14.setLocation("San Francisco, CA");
            jobApplication14.setPackage_lpa("22");
            jobApplication14.setDiscreption("Looking for a product manager to lead new product launches.");
            jobApplication14.setNo_rounds("3");
            jobApplication14.setApply_before("2024-10-01");

            jobApplicationRepo.save(jobApplication14);

            // Create Job Application 15
            JobApplication jobApplication15 = new JobApplication();
            jobApplication15.setCompany_name("Uber");
            jobApplication15.setType("Full-Time");
            jobApplication15.setRole("Operations Manager");
            jobApplication15.setStatus("Open");
            jobApplication15.setLocation("San Francisco, CA");
            jobApplication15.setPackage_lpa("19");
            jobApplication15.setDiscreption("Seeking an operations manager for ride-hailing logistics.");
            jobApplication15.setNo_rounds("3");
            jobApplication15.setApply_before("2024-09-20");

            jobApplicationRepo.save(jobApplication15);

            // Create Job Application 16
            JobApplication jobApplication16 = new JobApplication();
            jobApplication16.setCompany_name("Dropbox");
            jobApplication16.setType("Full-Time");
            jobApplication16.setRole("Security Engineer");
            jobApplication16.setStatus("Open");
            jobApplication16.setLocation("San Francisco, CA");
            jobApplication16.setPackage_lpa("20");
            jobApplication16.setDiscreption("We are hiring a security engineer to secure our cloud storage solutions.");
            jobApplication16.setNo_rounds("4");
            jobApplication16.setApply_before("2024-10-25");

            jobApplicationRepo.save(jobApplication16);

            // Create Job Application 17
            JobApplication jobApplication17 = new JobApplication();
            jobApplication17.setCompany_name("LinkedIn");
            jobApplication17.setType("Full-Time");
            jobApplication17.setRole("Backend Engineer");
            jobApplication17.setStatus("Open");
            jobApplication17.setLocation("Sunnyvale, CA");
            jobApplication17.setPackage_lpa("22");
            jobApplication17.setDiscreption("Hiring a backend engineer to work on LinkedIn's social network infrastructure.");
            jobApplication17.setNo_rounds("3");
            jobApplication17.setApply_before("2024-11-30");

            jobApplicationRepo.save(jobApplication17);

            // Create Job Application 18
            JobApplication jobApplication18 = new JobApplication();
            jobApplication18.setCompany_name("Stripe");
            jobApplication18.setType("Full-Time");
            jobApplication18.setRole("Payment Systems Engineer");
            jobApplication18.setStatus("Open");
            jobApplication18.setLocation("San Francisco, CA");
            jobApplication18.setPackage_lpa("21");
            jobApplication18.setDiscreption("Looking for a payment systems engineer to handle our global payment infrastructure.");
            jobApplication18.setNo_rounds("3");
            jobApplication18.setApply_before("2024-12-10");

            jobApplicationRepo.save(jobApplication18);

            // Create Job Application 19
            JobApplication jobApplication19 = new JobApplication();
            jobApplication19.setCompany_name("Zoom");
            jobApplication19.setType("Full-Time");
            jobApplication19.setRole("Video Streaming Engineer");
            jobApplication19.setStatus("Open");
            jobApplication19.setLocation("San Jose, CA");
            jobApplication19.setPackage_lpa("20");
            jobApplication19.setDiscreption("Hiring a video streaming engineer to enhance Zoom's video capabilities.");
            jobApplication19.setNo_rounds("3");
            jobApplication19.setApply_before("2024-09-25");

            jobApplicationRepo.save(jobApplication19);

            // Create Job Application 20
            JobApplication jobApplication20 = new JobApplication();
            jobApplication20.setCompany_name("Twitter");
            jobApplication20.setType("Full-Time");
            jobApplication20.setRole("Social Media Analyst");
            jobApplication20.setStatus("Open");
            jobApplication20.setLocation("San Francisco, CA");
            jobApplication20.setPackage_lpa("18");
            jobApplication20.setDiscreption("We are looking for a social media analyst to help grow Twitter's user base.");
            jobApplication20.setNo_rounds("2");
            jobApplication20.setApply_before("2024-10-05");

            jobApplicationRepo.save(jobApplication20);

            log.info("All job applications added successfully");

        } catch (Throwable e) {
            log.error("Something went wrong while inserting default job application records", e);
        }
    }
}
