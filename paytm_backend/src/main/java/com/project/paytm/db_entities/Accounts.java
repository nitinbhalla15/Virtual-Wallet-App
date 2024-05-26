package com.project.paytm.db_entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer account_id;
    private Long amount;

//    @OneToOne(mappedBy = "accounts")
//    private UserEntity userEntity;

}
