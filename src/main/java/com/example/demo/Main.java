package com.example.demo;

import com.example.demo.enums.Location;
import com.example.demo.model.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo");
    static EntityManager em = emf.createEntityManager();
    static EntityTransaction transaction = em.getTransaction();

    public static void main(String[] args) {
        System.out.println("App Start");
        Main ob = new Main();

        loadData();

        fetchData(ob);
        System.out.println("App Stop");
    }

    private static void fetchData(Main ob) {
        //        ob.addStudent(job1);
//        ob.addStudent(jobEntity2);
//        ob.update(1);
        ob.get(1);
//        ob.delete(1);

//        ob.getJobsWithApplication();
//        ob.getAllInterviewsWithinWeek();
//        ob.getAllJobsWithCompaniesAddress();
//        ob.getAllSkillsForJobWithSalary();
//        ob.getAllRecruiterWithSalary();
//        ob.getAllJobWithInterview();
    }

    private static void loadData() {
        // Recruiter
        Recruiter recruiter1 = new Recruiter();
        recruiter1.setName("K-force");
        recruiter1.setAddress("Fairfield");

        Recruiter recruiter2 = new Recruiter();
        recruiter2.setName("Delta");
        recruiter2.setAddress("Dallas");

        // Company Client
        Client client1 = new Client();
        client1.setName("JP-Morgan");
        client1.setAddress("Texas");
        client1.setMission("Complete Software Solution");
        client1.setReason("Donot know");
        client1.setWebsite("Www.jp-morgan.com");
        client1.setRecruiter(recruiter1);

        Client client2 = new Client();
        client2.setName("Deerwalk");
        client2.setAddress("Greenwich CT");
        client2.setMission("Complete Health care solution");
        client2.setReason("new hire");
        client2.setWebsite("Www.deerwalk.com");
        client2.setRecruiter(recruiter2);

        List<Client> clientList = new ArrayList<>();
        clientList.add(client1);
        clientList.add(client2);

        // Job
        Job job1 = new Job();
        job1.setTitle("Software Developer");
        job1.setSalary(14235);
        job1.setCompany(recruiter1);

        Job job2 = new Job();
        job2.setTitle("Java Developer");
        job2.setSalary(90000);
        job2.setCompany(client1);

        // Skill
        Skill skill1 = new Skill();
        skill1.setDescription("Full Stack");
        skill1.setExperience("Five year");
        skill1.setName("Technical");
        skill1.setLanguage("C");
        skill1.setJob(job2);

        Skill skill2 = new Skill();
        skill2.setDescription("Full Stack");
        skill2.setExperience("Two year");
        skill2.setName("Technical");
        skill2.setLanguage("Java");
        skill2.setJob(job1);

        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill1);
        skillList.add(skill2);

        // Interview
        TechnicalInterview technicalInterview1 = new TechnicalInterview();
        technicalInterview1.setDuration(1);
        technicalInterview1.setLocation(Location.ONLINE);
        technicalInterview1.setDate(LocalDate.now());
        technicalInterview1.setQuestion("Any previous knowledge");
        technicalInterview1.setEmail("sanjayakoju@gmail.com");
        technicalInterview1.setPhoneNumber("6418192555");
        technicalInterview1.setJob(job1);

        ScreeningInterview screeningInterview1 = new ScreeningInterview();
        screeningInterview1.setName("Sanjaya Koju");
        screeningInterview1.setDate(LocalDate.now());
        screeningInterview1.setResult("Passed");
        screeningInterview1.setPhoneNumber("6418192555");
        screeningInterview1.setEmail("sanjayakoju42@gmail.com");
        screeningInterview1.setJob(job1);

        HiringManagerInterview hiringManagerInterview1 = new HiringManagerInterview();
        hiringManagerInterview1.setDate(LocalDate.now());
        hiringManagerInterview1.setEmail("jpmorgama@gmail.com");
        hiringManagerInterview1.setPhoneNumber("2935725235");
        hiringManagerInterview1.setStartDate(LocalDate.now());
        hiringManagerInterview1.setTeamSize(5);
        hiringManagerInterview1.setJob(job1);

        // Application
        Application application1 = new Application();
        application1.setDate(LocalDate.of(2022, 12, 01));
        application1.setDate(LocalDate.now());
        application1.setVersion("1.0.0");
        application1.setJob(job1);

        Application application2 = new Application();
        application2.setDate(LocalDate.of(2022, 10, 23));
        application2.setDate(LocalDate.now());
        application2.setVersion("1.0.0");
        application2.setJob(job2);


        transaction.begin();
        em.persist(application1);
        em.persist(application2);
        em.persist(job1);
        em.persist(job2);
        em.persist(skill1);
        em.persist(skill2);
        em.persist(recruiter1);
        em.persist(recruiter2);
        em.persist(client1);
        em.persist(client2);
        em.persist(technicalInterview1);
        em.persist(hiringManagerInterview1);
        em.persist(screeningInterview1);
        transaction.commit();
    }

    // Todo 1- Write a query to return all Jobs with an Application. (native query)
    public void getJobsWithApplication() {
        String queryString1 = "SELECT * from job as j join application as a on j.id = a.job_id";
        Query query1 = em.createNativeQuery(queryString1, Job.class);
        List<Job> jobs = query1.getResultList();
        System.out.println("1- Write a query to return all Jobs with an Application. (native query)");
        System.out.println("Job :"+jobs);
    }

//    Todo  2- Write a query to return all Interviews within a week. (Dynamic query)
      public void getAllInterviewsWithinWeek() {
        String queryString = "SELECT i from Interview as i where i.date between '2022-7-13' and '2022-7-20'";
        Query query = em.createQuery(queryString);
        List<Interview> interviewList = query.getResultList();
        System.out.println(" 2- Write a query to return all Interviews within a week. (Dynamic query)");
        System.out.println(interviewList);
      }

      // Todo 3- Write a query to return all Jobs with Companies in a specific state (Named Query)
    public void getAllJobsWithCompaniesAddress() {
        Query query = em.createNamedQuery("findJobByCompanyAddress", Job.class);
        query.setParameter(1, "Texas");
//        query.setParameter("address", "Texas");
        List<Job> jobList = query.getResultList();

        System.out.println("3- Write a query to return all Jobs with Companies in a specific state (Named Query)");
        System.out.println(jobList);
    }

    // Todo 4- Write a query to return all Skills for Jobs with salary > a certain amount and with a company in a specific state. (Criteria API)
    public void getAllSkillsForJobWithSalary() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        CriteriaQuery<Skill> skillCriteriaQuery = criteriaBuilder.createQuery(Skill.class);
        Root<Skill> skillRoot = skillCriteriaQuery.from(Skill.class);
        skillCriteriaQuery.select(skillRoot);
        Join<Skill, Job> joinJob = skillRoot.join("job");

        Predicate salaryPredicate = criteriaBuilder.ge(joinJob.get("salary"), 1000);
        Join<Job, Company> joinCompany = joinJob.join("company");
        Predicate addressPredicate = criteriaBuilder.equal(joinCompany.get("address"), "texas");

        Predicate finalPredicate = criteriaBuilder.and(salaryPredicate, addressPredicate);
        skillCriteriaQuery.where(finalPredicate);

        Query query = em.createQuery(skillCriteriaQuery);
        List<Skill> skillList = query.getResultList();
        System.out.println("4- Write a query to return all Skills for Jobs with salary > a certain amount and with a company in a specific state. (Criteria API)");
        System.out.println(skillList);
    }

//  Todo  5- Write a query to return all Recruiters with Jobs paying more than a certain amount.
    public void getAllRecruiterWithSalary() {
        //  Using Criteria
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Recruiter> criteriaQuery = criteriaBuilder.createQuery(Recruiter.class);
        Root<Recruiter> recruiterRoot = criteriaQuery.from(Recruiter.class);
        criteriaQuery.select(recruiterRoot);

        Join<Recruiter, Company> joinCompany = recruiterRoot.join("clients");
        Join<Company, Job> joinJob = joinCompany.join("job");

        Predicate jobPredicate = criteriaBuilder.ge(joinJob.get("salary") , 1000);

        criteriaQuery.where(jobPredicate);
        Query query = em.createQuery(criteriaQuery);

//        String stringQuery = "SELECT * from recruiter as r join company as c on r.ID = c.ID join job as j on j.COMPANY_ID = c.id where j.SALARY > ?1";
//        Query query = em.createNativeQuery(stringQuery, Recruiter.class);
//        query.setParameter(1, 1000);
        List<Recruiter> recruiterList = query.getResultList();
        System.out.println("5- Write a query to return all Recruiters with Jobs paying more than a certain amount.");
        System.out.println(recruiterList);
    }

//  Todo  6- Write a query to return all Jobs with at least 2 interviews.
    public void getAllJobWithInterview() {
        TypedQuery query = em.createQuery("SELECT j FROM Job j WHERE (select count(i) from Interview i where i.job.id=j.id)>=2",Job.class);
        List<Job>  jobList = query.getResultList();
        System.out.println("6- Write a query to return all Jobs with at least 2 interviews.");
        System.out.println(jobList);
    }

    public void addStudent(Job jobEntity) {
        transaction.begin();
        em.persist(jobEntity);
        transaction.commit();
    }

    public void update(int id) {
        Job jobEntity = em.find(Job.class, id);
        transaction.begin();
        jobEntity.setSalary(9000);
        transaction.commit();
    }

    public void get(int id) {
        transaction.begin();
        Job jobEntity = em.find(Job.class,id);
        System.out.println(jobEntity);
        transaction.commit();
    }

    public void delete(int id) {
        transaction.begin();
        Job jobEntity = em.find(Job.class,id);
        em.remove(jobEntity);
        transaction.commit();
    }
}
