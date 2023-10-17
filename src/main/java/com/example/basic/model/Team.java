package com.example.basic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data //toString()를 Override하고 있음
@ToString(exclude = {"players"})//나는 player에 대한 내용은 생략할꺼야>>안하면 stackoverflow오류뜸
public class Team {
@Id
int teamId;
//team_id으로 나타남
String teamName;

@JsonIgnore
@OneToMany(mappedBy = "team") //mappedBy : 나를 뭐라부르나?
List<Player> players = new ArrayList<>();

}