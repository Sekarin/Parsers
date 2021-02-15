import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser1_0 {
    public static void main(String[] args) throws IOException {
        Document page = GetPage();

        //получаем таблицу валют
        Elements table = page.select("div[class=finance-currency-table__body]");
//        System.out.println(table.text());

        //получаем строку с монетой
        Elements stringValue = table.select("a[class=finance-currency-table__tr]");
//        System.out.println(stringValue.text());

        //получаем код с монетой
        Elements moneyCode = stringValue.select("div.finance-currency-table__cell--code");
//        System.out.println(moneyCode.text());

        //получаем номинал монеты
        Elements moneyNominal = stringValue.select("div.finance-currency-table__cell--denomination");
//        System.out.println(moneyNominal.text());

        //получаем название монеты
        Elements moneyName = stringValue.select("div.finance-currency-table__cell--currency");
//        System.out.println(moneyName.text());

        //поулчаем курс монеты
        Elements moneyCourse = stringValue.select("div.finance-currency-table__cell--value");
//        System.out.println(moneyCourse.text());

        String space = " ";
        System.out.println("Код\t\tНоминал\tКурс\t\tИмя");
        for (int i = 0; i < moneyCode.size(); i++) {

            String code = moneyCode.get(i).text();
            String nominal = moneyNominal.get(i).text() + space.repeat(5 - moneyNominal.get(i).text().length());
            String name = moneyName.get(i).text();
            String course = moneyCourse.get(i).text() + space.repeat(10 - moneyCourse.get(i).text().length());
            System.out.println(code + "\t\t" + nominal + "\t" + course + "\t"  + name);
        }

    }

    public static Document GetPage () throws IOException {
        String url = "https://finance.rambler.ru/currencies/";

        Document page = Jsoup.connect(url).get();
        return page;
    }

}
