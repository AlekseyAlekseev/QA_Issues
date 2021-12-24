package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IssuesRepositoryTest {

    private IssuesRepository repo = new IssuesRepository();

    private HashSet<String> label1 = new HashSet<>(Collections.singletonList("status: in progress"));
    private HashSet<String> label2 = new HashSet<>(Arrays.asList("status: in progress", "type: new feature"));

    private Issue issueAlexOpen1 = new Issue(105400, "Alex", "bug report", "bug",
            true, label1, "Valera", 20211223);
    private Issue issueAlexOpen2 = new Issue(103400, "Alex", "bug report", "bug",
            true, label2, "Kirill", 20211126);
    private Issue issueAlexClose3 = new Issue(101467, "Alex", "bug report", "bug",
            false, label2, "Kirill", 20210817);

    private Issue issueJohnOpen1 = new Issue(100320, "John", "bugfix", "debug",
            true, label1, "Kirill", 20211228);
    private Issue issueJohnOpen2 = new Issue(100520, "John", "bugfix", "debug",
            true, label2, "Max", 20210705);
    private Issue issueJohnClose3 = new Issue(107620, "John", "bugfix", "debug",
            false, label2, "Dasha", 20210813);
    private Issue issueJohnClose4 = new Issue(109165, "John", "bugfix", "debug",
            false, label2, "Kirill", 20211021);


    @Test
    void shouldAddIssue() {
        repo.addIssue(issueAlexOpen1);
        assertEquals(1, repo.getIssues().size());
    }

    @Test
    void shouldGetOpenIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueJohnOpen1);
        repo.addIssue(issueAlexClose3);
        List<Issue> expected = List.of(issueAlexOpen1, issueJohnOpen1);
        List<Issue> actual = repo.getOpenIssue();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldGetCloseIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueJohnOpen1);
        repo.addIssue(issueAlexClose3);
        repo.addIssue(issueJohnClose3);
        List<Issue> expected = List.of(issueAlexClose3, issueJohnClose3);
        List<Issue> actual = repo.getCloseIssue();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldFilterByNameOpenIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueAlexOpen2);
        repo.addIssue(issueAlexClose3);
        repo.addIssue(issueJohnOpen2);
        repo.addIssue(issueJohnClose3);

        List<Issue> expected = List.of(issueAlexOpen1, issueAlexOpen2);
        List<Issue> actual = repo.filterByNameOpen("Alex");

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldFilterByNameCloseIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueAlexClose3);
        repo.addIssue(issueJohnOpen2);
        repo.addIssue(issueJohnClose3);
        repo.addIssue(issueJohnClose4);

        List<Issue> expected = List.of(issueJohnClose3, issueJohnClose4);
        List<Issue> actual = repo.filterByNameClose("John");

        assertArrayEquals(expected.toArray(), actual.toArray());
    }


    @Test
    void shouldFilterByLabelOpenIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueAlexOpen2);
        repo.addIssue(issueAlexClose3);
        repo.addIssue(issueJohnOpen2);
        repo.addIssue(issueJohnClose3);

        List<Issue> expected = List.of(issueAlexOpen2, issueJohnOpen2);
        List<Issue> actual = repo.filterByLabelOpenIssue(label2);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldFilterByLabelCloseIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueAlexOpen2);
        repo.addIssue(issueAlexClose3);
        repo.addIssue(issueJohnOpen2);
        repo.addIssue(issueJohnClose3);
        repo.addIssue(issueJohnClose4);

        List<Issue> expected = List.of(issueAlexClose3, issueJohnClose3, issueJohnClose4);
        List<Issue> actual = repo.filterByLabelCloseIssue(label2);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldFilterByAssigneeOpenIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueAlexOpen2);
        repo.addIssue(issueAlexClose3);
        repo.addIssue(issueJohnOpen1);
        repo.addIssue(issueJohnClose4);

        List<Issue> expected = List.of(issueAlexOpen2, issueJohnOpen1);
        List<Issue> actual = repo.filterByAssigneeOpenIssue("Kirill");

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldFilterByAssigneeCloseIssue() {
        repo.addIssue(issueAlexOpen1);
        repo.addIssue(issueAlexOpen2);
        repo.addIssue(issueAlexClose3);
        repo.addIssue(issueJohnOpen1);
        repo.addIssue(issueJohnClose3);
        repo.addIssue(issueJohnClose4);

        List<Issue> expected = List.of(issueAlexClose3, issueJohnClose4);
        List<Issue> actual = repo.filterByAssigneeCloseIssue("Kirill");

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldCloseIssue() {
        repo.addIssue(issueJohnOpen1);

        repo.closeIssue(100320);

        List<Issue> expected = List.of(issueJohnOpen1);
        List<Issue> actual = repo.getCloseIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldCloseInvalidIdIssue() {
        repo.addIssue(issueJohnOpen1);

        repo.closeIssue(101976);

        List<Issue> expected = List.of();
        List<Issue> actual = repo.getCloseIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldOpenIssue() {
        repo.addIssue(issueAlexClose3);

        repo.openIssue(101467);

        List<Issue> expected = List.of(issueAlexClose3);
        List<Issue> actual = repo.getOpenIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void shouldOpenInvalidIdIssue() {
        repo.addIssue(issueAlexClose3);

        repo.openIssue(104632);

        List<Issue> expected = List.of();
        List<Issue> actual = repo.getOpenIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
