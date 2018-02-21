package com.miguelhalys.hazelcast.domain;

import java.io.Serializable;

public class Employee implements Serializable {
  private String name;
  private int age;
  private boolean active;
  private double salary;

  public Employee(String name, int age, boolean active, double salary) {
    this.name = name;
    this.age = age;
    this.active = active;
    this.salary = salary;
  }

  public Employee() {
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public double getSalary() {
    return salary;
  }

  public boolean isActive() {
    return active;
  }

@Override
public String toString() {
	return "Employee [name=" + name + ", age=" + age + ", active=" + active + ", salary=" + salary + "]";
}
  
  
}