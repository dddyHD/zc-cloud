package cn.suancloud.springBoot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apply")
public class Apply implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "applicant")
  private String applicant;
  @Column(name = "project")
  private String project;
  @Column(name = "type")
  private String type;
  @Column(name = "approver")
  private String approver;
  @Column(name = "is_agree")
  private Boolean isAgree;
  @Column(name = "status")
  private Boolean status;
  @Column(name = "application_time")
  private Date applicationTime = new Date();
  @Column(name = "approval_time")
  private Date approvalTime;
  @Column(name ="reason")
  private String reason;
  @Column(name = "remark")
  private String remark;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getApplicant() {
    return applicant;
  }

  public void setApplicant(String applicant) {
    this.applicant = applicant;
  }

  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getApprover() {
    return approver;
  }

  public void setApprover(String approver) {
    this.approver = approver;
  }

  public Boolean getAgree() {
    return isAgree;
  }

  public void setAgree(Boolean agree) {
    isAgree = agree;
  }

  public Date getApplicationTime() {
    return applicationTime;
  }

  public void setApplicationTime(Date applicationTime) {
    this.applicationTime = applicationTime;
  }

  public Date getApprovalTime() {
    return approvalTime;
  }

  public void setApprovalTime(Date approvalTime) {
    this.approvalTime = approvalTime;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}
