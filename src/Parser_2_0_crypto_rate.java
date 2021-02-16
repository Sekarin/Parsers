import org.jsoup.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser_2_0_crypto_rate {
    public static void main(String[] args) throws IOException {
        Document page = getPage();

        //запрос таблицы
        Elements table = page.select("tbody");
//        System.out.println(table);

        //запрос строки крипты
        Elements cryptoString = table.select("tr");
        Elements cryptoString1 = cryptoString.select("td");
//        System.out.println(cryptoName);

        //запрос названия крипты
        Elements cryptoName = cryptoString1.select("td[class=left bold elp name cryptoName first js-currency-name]");
//        System.out.println(cryptoName.text());

        //запрос тикера крипты(сокр нахзвание)
        Elements cryptoTicker = cryptoString1.select("td[class=left noWrap elp symb js-currency-symbol]");
//        System.out.println(cryptoTicker.text());

        //запрос цена крипты
        Elements cryptoCurrency = cryptoString1.select("td[class=price js-currency-price]");
//        System.out.println(cryptoCurrency.text());

        String space = " ";

        for (int i = 0; i < cryptoCurrency.size(); i++) {
            String name = cryptoName.get(i).text() + space.repeat(16 - cryptoName.get(i).text().length());
            String ticket = cryptoTicker.get(i).text() + space.repeat(4 - cryptoTicker.get(i).text().length());
            String currency = cryptoCurrency.get(i).text() + space.repeat(12 - cryptoCurrency.get(i).text().length());
            System.out.println(name + ticket + "\t" + currency);
        }
    }

    public static Document getPage () throws IOException {
        String url = "https://ru.investing.com/crypto/";

        return Jsoup.connect(url).get();
    }
}
