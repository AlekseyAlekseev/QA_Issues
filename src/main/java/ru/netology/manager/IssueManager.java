package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.ru.netology.repository.IssuesRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueManager {

    private IssuesRepository repo = new IssuesRepository();


    public void addIssue(Issue issue) {
        repo.addIssue(issue);
    }

    public List<Issue> getOpenIssue() {
        return repo.getOpenIssue();
    }

    public List<Issue> getCloseIssue() {
        return repo.getCloseIssue();
    }

    public List<Issue> filterByNameClose(String author) {
        return repo.filterByNameClose(author);
    }

    public List<Issue> filterByNameOpen(String author) {
        return repo.filterByNameOpen(author);
    }

    public List<Issue> filterByLabelOpenIssue(HashSet<String> labels) {
        return repo.filterByLabelOpenIssue(labels);
    }

    public List<Issue> filterByLabelCloseIssue(HashSet<String> labels) {
        return repo.filterByLabelCloseIssue(labels);
    }

    public List<Issue> filterByAssigneeOpenIssue(String assignee) {
        return repo.filterByAssigneeOpenIssue(assignee);
    }

    public List<Issue> filterByAssigneeCloseIssue(String assignee) {
        return repo.filterByAssigneeCloseIssue(assignee);
    }

    public void closeIssue(int id) {
        repo.closeIssue(id);
    }

    public void openIssue(int id) {
        repo.openIssue(id);
    }

    /**
     * Сортирует список по возрастанию даты создания задачи
     * @param issues список задач
     */
    public void sortIssue(List<Issue> issues) {
        Collections.sort(issues);
    }
}

