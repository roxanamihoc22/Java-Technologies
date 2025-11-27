package com.example.PrefSchedule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {
        this.role = Role.ADMIN;
    }
}
