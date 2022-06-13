import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void shouldDataInForm() {
        Configuration.holdBrowserOpen=true;
        open("http://localhost:9999");
        String FirstDate = DataGenerate.generateDate(3);
        String SecondDate = DataGenerate.generateDate(9);


        $("[data-test-id='city'] input").setValue(DataGenerate.generateCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys. DELETE);
        $("[data-test-id=date] input").val(FirstDate);
        $("[name='name']").setValue(DataGenerate.generateName());
        $("[name='phone']").setValue(DataGenerate.generatePhone());
        $(By.className("checkbox__text")).click();
        $(By.className("button__content")).click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + FirstDate));

        $("[data-test-id=date] input").doubleClick().sendKeys(Keys. DELETE);
        $("[data-test-id=date] input").val(SecondDate);
        $(By.className("button__content")).click();
        $(withText("У вас уже запланирована встреча на другую дату."))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + SecondDate));









    }
}
