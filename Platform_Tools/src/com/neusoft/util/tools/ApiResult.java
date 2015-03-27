package com.neusoft.util.tools;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ApiResult
  implements Serializable
{
  private static final long serialVersionUID = -494469572117119916L;
  private String id;
  private boolean result;
  private String message;
  private String url;
  private String suggest;
  private Date datetime;
  private String tid;
  private String orgi;
  private Serializable data;
  private List<Serializable> dataList;

  public String getId()
  {
    return this.id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public boolean isResult() {
    return this.result;
  }
  public void setResult(boolean result) {
    this.result = result;
  }
  public String getMessage() {
    return this.message;
  }
  public void setMessage(String message) {
    this.message = message;
  }
  public String getUrl() {
    return this.url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getSuggest() {
    return this.suggest;
  }
  public void setSuggest(String suggest) {
    this.suggest = suggest;
  }
  public Date getDatetime() {
    return this.datetime;
  }
  public void setDatetime(Date datetime) {
    this.datetime = datetime;
  }
  public String getTid() {
    return this.tid;
  }
  public void setTid(String tid) {
    this.tid = tid;
  }
  public String getOrgi() {
    return this.orgi;
  }
  public void setOrgi(String orgi) {
    this.orgi = orgi;
  }
  public Serializable getData() {
    return this.data;
  }
  public void setData(Serializable data) {
    this.data = data;
  }
  public List<Serializable> getDataList() {
    return this.dataList;
  }
  public void setDataList(List<Serializable> dataList) {
    this.dataList = dataList;
  }
}