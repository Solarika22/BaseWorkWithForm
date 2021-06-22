package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WorkWithForm {
    String firstName = "Egor",
            lastName = "Egor",
            email = "aaa@aaa.ru",
            gender = "Other",
            number = "+79878778787871",
            monthOfBirth = "September",
            yearOfBirth = "1988",
            dayOfBirth = "22",
            subject1 = "Chemistry",
            hobby1 = "Sports",
            picture = "img/1.png",
            currentAddress = "Montenegro 123",
            state = "Uttar Pradesh",
            city = "Merrut";





    @Test
    void successfulRegistrationTest() {
        //Arrange / Given / Open site
        open("https://demoqa.com/automation-practice-form");

        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        // Act / When / Fill the form
        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $(".genterWrapper").$(byText(gender)).click();
        //Другие вариенты поиска по гендеру
        //(byText(gender)).click();
        // ("[for-gender-radio-1]").click()


        $("#userNumber").val(number);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);

        // String.format("hello %s %s", "SOME", "World") == "hello SOME World";
        // - Т.е. в первое выражение подставится где %s, все что идет через запятую после него
        //  $(String.format("[aria-label='Choose %s, %s %sth, %s']",
        //         dayWeekBirth, monthOfBirth, dayOfBirth,yearOfBirth)).click();

        //Тут указываем, что выбрать нужно селектор первый а не второй, конструкция :not
        $(String.format(".react-datepicker__day react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", dayOfBirth)).click();

        $("#subjectsInput").val(subject1).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby1)).click();

        // Способы загрузки файлов
        // Указали путь до изображения + название изображения
        // uploadFromClasspath означает, что с папки resourses, что-то подгружается
        $("#uploadPicture").uploadFromClasspath("img/" + picture);
        //Cпособ номер 2
        //  $("#uploadPicture").uploadFile(new File("src/test/resources/img/" + picture));

        $("#currentAddress").val(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        // Assert / Then / Verify successfull form submit
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        //#1
        $(".table-responsive").shouldHave(text(firstName + " " + lastName), text(email), text(gender));
        // #2 Еще один вариант реализации
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        //#3
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text(gender));
        $x("//td[text()='Mobile']").parent().shouldHave(text(number));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $x("//td[text()='Subjects']").parent().shouldHave(text(subject1));
        $x("//td[text()='Hobbies']").parent().shouldHave(text(hobby1));
        $x("//td[text()='Picture']").parent().shouldHave(text(picture));
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));
    }
}
