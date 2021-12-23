package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable<Issue> {

    private int id; // номер задачи
    private String author; // автор задачи
    private String name; // название задачи
    private String comment; // описание задачи
    private boolean state; // открыта или закрыта
    private HashSet<String> label; // теги
    private String assignee; // на кого назначено
    private int date; // дата создания задачи

    @Override
    public int compareTo(Issue o) {
        return Integer.compare(date, o.date);
    }
}

