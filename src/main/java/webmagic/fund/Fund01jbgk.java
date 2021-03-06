package webmagic.fund;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import util.TxtUtil;
import webmagic.JavaTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 911
 * @date 2020-08-04 10:43
 */
public class Fund01jbgk  implements PageProcessor,Runnable {

    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(10000);
    static String path = "C:\\Users\\xiala\\Desktop\\123\\jbgk.txt";
    static String target = "C:\\Users\\xiala\\Desktop\\123\\jbgk123.txt";
    static List<String> list = TxtUtil.readTxt(path);
    StringBuilder all = new StringBuilder();

    @Override
    public void process(Page page) {
        all.append(TxtUtil.readTxtStr(target));
        Html html = page.getHtml();
        Document document = html.getDocument();
        Element element = document.getElementsByClass("box").first();

        StringBuilder sb = new StringBuilder();
        List<Element> elements = element.getElementsByTag("td");
        for (int i = 0; i < 12; i++) {
            String str = elements.get(i).text();
//            System.out.println(str);
            sb.append(str+"\t");
        }
        all.append(sb.toString()+"\n");
        TxtUtil.writeTxt(target,all.toString());
        System.out.println(sb.toString());
//        Element element = document.getElementsByClass("t_fsz").first();
//        System.out.println(element.text());
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        for (int i = 0; i < list.size(); i++) {
            String url = list.get(i);//.split(",")[1];
            System.out.println((i+1)+"\t"+url);
            Spider.create(new Fund01jbgk()).addUrl(url).thread(5).run();
        }

    }

    @Override
    public void run() {

    }

//    @Test
//    public void getInfo(){
//        String path = "C:\\Users\\xiala\\Desktop\\123\\1233.txt";
//        List<String> list = TxtUtil.readTxt(path);
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i <list.size() ; i++) {
//            String str = list.get(i);
//            str = str.replace("http://fund.eastmoney.com/","http://fundf10.eastmoney.com/jbgk_");
//            sb.append(str+"\n");
//            System.out.println(str);
//        }
//        TxtUtil.writeTxt("C:\\Users\\xiala\\Desktop\\123\\jbgk.txt",sb.toString());
//    }
}
