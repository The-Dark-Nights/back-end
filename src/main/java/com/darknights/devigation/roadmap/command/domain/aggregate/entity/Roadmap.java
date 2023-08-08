package com.darknights.devigation.roadmap.command.domain.aggregate.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ROADMAP_TB")
@NoArgsConstructor
@Getter
public class Roadmap {
    @Id
}
