package io.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String USER_AGENT = "";
    private static final String USER_AGENT2 = "";

    public static void main(String[] agrs) throws IOException {
        // parse the website
        ArrayList<String> queryList = new ArrayList<>();
        queryList.add("Eye%20Cream");
//        queryList.add("Wall%20Mount%20Shelf");
//        queryList.add("iphone case");

        //crawl all of the website
        for (int i = 0; i < queryList.size(); i++) {
            String requestUrl = "https://www.amazon.com/s/ref=nb_sb_noss?field-keywords=" + queryList.get(i);
            syncCrawl(requestUrl);
        }

    }

    private static void syncCrawl(String requestUrl) throws IOException {
        //Document doc = Jsoup.connect(requestUrl).userAgent(USER_AGENT).timeout(1000).get();

        Document doc = Jsoup.connect(requestUrl).get();

        // Document???
        // Jsoup package?? connect..
        Elements prods = doc.getElementsByAttribute("title");
        // return all of the tag that inclued title
        // what is the different with doc.select


        for (int i = 0; i < prods.size(); i++) {
       //     System.out.println("prod title= " + prods.get(i).attr("title"));
        }
        Elements prodsLink = doc.getElementsByClass("a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal");
        System.out.println("num of prod link: " + prodsLink.size());
        for (int i = 0; i < prodsLink.size(); i++) {
        //    System.out.println("prod link=" + prodsLink.get(i).attr("href"));
        }
        //<a class="a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal" title="Neocutis Lumiere Bio-Restorative Eye Cream 0.5oz" href="/gp/slredirect/picassoRedirect.html/ref=pa_sp_atf_aps_sr_pg1_2?ie=UTF8&amp;adId=A0167608WKPNQW5ZLVLH&amp;url=https%3A%2F%2Fwww.amazon.com%2FNeocutis-Lumiere-Bio-Restorative-Cream-0-5oz%2Fdp%2FB00BCNTZS2%2Fref%3Dsr_1_2_sspa%3Fie%3DUTF8%26qid%3D1522713689%26sr%3D8-2-spons%26keywords%3DEye%2BCream%26psc%3D1&amp;qualifier=1522713689&amp;id=3271735892235505&amp;widgetName=sp_atf"><h2 data-attribute="Neocutis Lumiere Bio-Restorative Eye Cream 0.5oz" data-max-rows="0" class="a-size-medium s-inline  s-access-title  a-text-normal"><span class="a-offscreen">[Sponsored]</span>Neocutis Lumiere Bio-Restorative Eye Cream 0.5oz</h2></a>

        String pageTitle = htmlTitle(doc);
        System.out.println("html title= " + pageTitle);
        List<String> urlElePaths = new ArrayList<>();
        urlElePaths.add("#result_0 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div.a-row.a-spacing-none.scx-truncate-medium.sx-line-clamp-2 > a");



        for (int j = 0; j < urlElePaths.size(); j++) {
            Element ele = doc.select(urlElePaths.get(j)).first();
            if (ele != null) {
                String detailUrl = ele.attr("href");
                System.out.println("datail url = " + detailUrl);
                String title = ele.attr("title");
                System.out.println("title= " + title);

            }
        }

    }

    public static String sendHttpGetRequest(String url) throws Exception {
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending GET reruest to URL: " + url);
        System.out.println("Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        int i = 0;
        while((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            i++;
        }
        System.out.println("number rof line:" + Integer.toString(i));
        in.close();
        String responseStr = response.toString();
        return responseStr;
    }
    public static String htmlTitle(Document doc) {
        Element node = doc.select("title").first();//first?
        if(node != null && node.text().length() > 0) {
            return node.text();
        }
        return null;
    }
}




