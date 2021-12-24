package ru.netology.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
public class IssuesRepository {

    private List<Issue> issues = new ArrayList<>();

    /**
     * Добавляет задачу в список
     *
     * @param issue задача
     */
    public void addIssue(Issue issue) {
        issues.add(issue);
    }

    /**
     * Возвращает список открытых задач
     *
     * @return список открытых задач
     */
    public List<Issue> getOpenIssue() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Возвращает список закрытых задач
     *
     * @return список закрытых задач
     */
    public List<Issue> getCloseIssue() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (!issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Возвращает список закрытых задач по автору
     *
     * @param author автор задачи
     * @return список == имени автора
     */
    public List<Issue> filterByNameClose(String author) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAuthor().equals(author) && !issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Возвращает список открытых задач по автору
     *
     * @param author автор задачи
     * @return список == имени автора
     */
    public List<Issue> filterByNameOpen(String author) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAuthor().equals(author) && issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Возвращает список открытых задач по тегам
     *
     * @param labels список тегов
     * @return список == set тегов
     */
    public List<Issue> filterByLabelOpenIssue(HashSet<String> labels) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getLabel() == labels && issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Возвращает список закрытых задач по тегам
     *
     * @param labels список тегов
     * @return список == set тегов
     */
    public List<Issue> filterByLabelCloseIssue(HashSet<String> labels) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getLabel() == labels && !issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Возвращает список открытых задач по назначеному
     *
     * @param assignee на кого назначена задача
     * @return список == назначеному
     */
    public List<Issue> filterByAssigneeOpenIssue(String assignee) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAssignee().equals(assignee) && issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Возвращает список закрытых задач по назначеному
     *
     * @param assignee на кого назначена задача
     * @return список == назначеному
     */
    public List<Issue> filterByAssigneeCloseIssue(String assignee) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAssignee().equals(assignee) && !issue.isState()) {
                tmp.add(issue);
            }
        }
        return tmp;
    }

    /**
     * Закрывает задачу по id
     *
     * @param id номер задачи
     */
    public void closeIssue(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setState(false);
            }
        }
    }

    /**
     * Открывает задачу по id
     *
     * @param id номер задачи
     */
    public void openIssue(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setState(true);
            }
        }
    }
}

