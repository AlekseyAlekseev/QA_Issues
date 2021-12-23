package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.Issue;
import ru.netology.ru.netology.repository.IssuesRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class IssueManagerTest {

    private IssuesRepository repo = Mockito.mock(IssuesRepository.class);
    private IssueManager manager = new IssueManager(repo);

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
    void shouldAddIssueAndGetOpenIssue() {
        List<Issue> returned = List.of(issueAlexOpen1);
        doReturn(returned).when(repo).getOpenIssue();

        manager.addIssue(issueAlexOpen1);
        List<Issue> expected = List.of(issueAlexOpen1);
        List<Issue> actual = manager.getOpenIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).getOpenIssue();
    }

    @Test
    void shouldGetCloseIssue() {
        List<Issue> returned = List.of(issueAlexClose3, issueJohnClose3, issueJohnClose4);
        doReturn(returned).when(repo).getCloseIssue();

        List<Issue> expected = List.of(issueAlexClose3, issueJohnClose3, issueJohnClose4);
        List<Issue> actual = manager.getCloseIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).getCloseIssue();
    }

    @Test
    void shouldSortIssue() {
        List<Issue> returned = List.of(issueAlexOpen1, issueAlexOpen2, issueJohnOpen1, issueJohnOpen2);
        doReturn(returned).when(repo).getOpenIssue();

        List<Issue> expected = List.of(issueJohnOpen2, issueAlexOpen2, issueAlexOpen1, issueJohnOpen1);
        List<Issue> tmp = manager.getOpenIssue();
        List<Issue> actual = new ArrayList<>(tmp);

        manager.sortIssue(actual);

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).getOpenIssue();
    }

    @Test
    void shouldFilterByNameOpenIssue() {
        List<Issue> returned = List.of(issueAlexOpen1, issueAlexOpen2);
        doReturn(returned).when(repo).filterByNameOpen("Alex");

        List<Issue> expected = List.of(issueAlexOpen1, issueAlexOpen2);
        List<Issue> actual = manager.filterByNameOpen("Alex");

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterByNameOpen("Alex");
    }

    @Test
    void shouldFilterByNameCloseIssue() {
        List<Issue> returned = List.of(issueJohnClose3, issueJohnClose4);
        doReturn(returned).when(repo).filterByNameClose("John");

        List<Issue> expected = List.of(issueJohnClose3, issueJohnClose4);
        List<Issue> actual = manager.filterByNameClose("John");

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterByNameClose("John");
    }

    @Test
    void shouldFilterByLabelOpenIssue() {
        List<Issue> returned = List.of(issueAlexOpen2, issueJohnOpen2);
        doReturn(returned).when(repo).filterByLabelOpenIssue(label2);

        List<Issue> expected = List.of(issueAlexOpen2, issueJohnOpen2);
        List<Issue> actual = manager.filterByLabelOpenIssue(label2);

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterByLabelOpenIssue(label2);
    }

    @Test
    void shouldFilterByLabelCloseIssue() {
        List<Issue> returned = List.of(issueAlexClose3, issueJohnClose3, issueJohnClose4);
        doReturn(returned).when(repo).filterByLabelCloseIssue(label2);

        List<Issue> expected = List.of(issueAlexClose3, issueJohnClose3, issueJohnClose4);
        List<Issue> actual = manager.filterByLabelCloseIssue(label2);

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterByLabelCloseIssue(label2);
    }

    @Test
    void shouldFilterByAssigneeOpenIssue() {
        List<Issue> returned = List.of(issueAlexOpen2, issueJohnOpen1);
        doReturn(returned).when(repo).filterByAssigneeOpenIssue("Kirill");

        List<Issue> expected = List.of(issueAlexOpen2, issueJohnOpen1);
        List<Issue> actual = manager.filterByAssigneeOpenIssue("Kirill");

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterByAssigneeOpenIssue("Kirill");
    }

    @Test
    void shouldFilterByAssigneeCloseIssue() {
        List<Issue> returned = List.of(issueAlexClose3, issueJohnClose4);
        doReturn(returned).when(repo).filterByAssigneeCloseIssue("Kirill");

        List<Issue> expected = List.of(issueAlexClose3, issueJohnClose4);
        List<Issue> actual = manager.filterByAssigneeCloseIssue("Kirill");

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).filterByAssigneeCloseIssue("Kirill");
    }

    @Test
    void shouldCloseIssue() {
        List<Issue> returned = List.of(issueJohnOpen1);
        doReturn(returned).when(repo).getCloseIssue();

        manager.closeIssue(100320);

        List<Issue> expected = List.of(issueJohnOpen1);
        List<Issue> actual = manager.getCloseIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).closeIssue(100320);
    }

    @Test
    void shouldOpenIssue() {
        List<Issue> returned = List.of(issueAlexClose3);
        doReturn(returned).when(repo).getOpenIssue();

        manager.openIssue(101467);

        List<Issue> expected = List.of(issueAlexClose3);
        List<Issue> actual = manager.getOpenIssue();

        assertArrayEquals(expected.toArray(), actual.toArray());

        verify(repo).openIssue(101467);
    }





}