package com.bigdata.Project2.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;


    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hireDate) {
        this.hiredate = hireDate;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int employeeId) {
        this.empno = employeeId;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String name) {
        this.ename = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String jobTitle) {
        this.job = jobTitle;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer salary) {
        this.sal = salary;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String image) {
        this.img = image;
    }

    private String ename;
    private String job;
    private Integer mgr;
    private String hiredate;
    private Integer sal;

    private Integer comm;
    private String img;

//    @ManyToOne
//    @JoinColumn(name = "deptno")
//    private Department department;

    private int department;


    public void setDepartment(int department) {
        this.department = department;
    }

    public int getDepartment() {
        return department;
    }


    public Employee(){}

    public Employee(int comm, Integer employeeId, String name, String job, int mgr, String hireDate, int salary, String image,int department) {
        this.comm = comm;
        this.empno = employeeId;
        this.ename = name;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hireDate;
        this.sal = salary;
        this.img = image;
        this.department = department;
    }


//    private String deptName;
//    private String location;
}
