package com.example.basic.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Player {
@Id
int playerId;
String playerName;

@ManyToOne
@JoinColumn(name = "team_id")
//team_id 으로 컬럼 생성
Team team;
// team_team_id 으로 컬럼 생성

// public String toString(){
//   return "Player 내용 : " + playerId + ", " + playerName;
// }
}
