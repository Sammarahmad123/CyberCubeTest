package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    private SelenideElement productPageTitle = $("span.title[data-test='title']");
    private SelenideElement productSortDropdown = $(".product_sort_container[data-test='product-sort-container']");
    private SelenideElement activeSortOption = $("span.active_option[data-test='active-option']");


    public boolean isOnProductPage() {
        boolean isTitleCorrect = productPageTitle.shouldBe(Condition.visible, Duration.ofMillis(6000)).text().equals("Products");
        boolean isSortDropdownPresent = productSortDropdown.shouldBe(Condition.visible, Duration.ofMillis(6000)).isDisplayed();
        boolean isDefaultSortSelected = activeSortOption.shouldBe(Condition.visible, Duration.ofMillis(6000)).text().equals("Name (A to Z)");
        return isTitleCorrect && isSortDropdownPresent && isDefaultSortSelected;
    }
}
