package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.getArticleTitle();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    //Ex5: Тест: сохранение двух статей
    @Test
    public void testSaveTwoArticlesToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String first_article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "LearningQA";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JUnit");
        SearchPageObject.clickByArticleWithSubstring("JUnit");
        String expected_second_article_title = ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToExistingMyList(name_of_folder);
        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(first_article_title);
        MyListsPageObject.checkDeletedArticleIsNotPresent(first_article_title);
        MyListsPageObject.tapOnSavedArticle(expected_second_article_title);
        String actual_article_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "We see unexpected title",
                expected_second_article_title,
                actual_article_title
        );
    }
}
