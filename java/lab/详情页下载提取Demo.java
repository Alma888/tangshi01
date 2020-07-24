package lab;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomText;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.List;

public class 详情页下载提取Demo {
    public static void main(String[] args) throws IOException {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setUseInsecureSSL(true);
            String url = "https://so.gushiwen.org/shiwenv_45c396367f59.aspx";
            HtmlPage page = webClient.getPage(url);
            HtmlElement body = page.getBody();
            /*
            List<HtmlElement> elements = body.getElementsByAttribute(
                    "div",
                    "class",
                    "contson"
            );

            for (HtmlElement element : elements) {
                System.out.println(element);
            }

            System.out.println(elements.get(0).getTextContent().trim());
             */

            //下面是《行宫》这首诗的解析，下去自己选择其他古诗去解析
            // 标题
            {
                String xpath = "//div[@class='cont']/h1/text()";
                //getByXPath() 方法得到的是一个List（查看源码）
                Object o = body.getByXPath(xpath).get(0);
                //将其转为真实的类型，它其实是一个DomText类型（DOM树）
                DomText domText = (DomText)o;
                //下面语句希望打印出来标题的内容
                System.out.println(domText.asText());
            }
        // 朝代
            {
                //下面语句后面的a标签a[1]意思是取第一个a标签朝代，这里的第一个是从下标1开始的，因为还有第二个a标签是作者
                String xpath = "//div[@class='cont']/p[@class='source']/a[1]/text()";
                Object o = body.getByXPath(xpath).get(0);DomText domText = (DomText)o;
                System.out.println(domText.asText());
            }
        // 作者
            {
                String xpath = "//div[@class='cont']/p[@class='source']/a[2]/text()";
                Object o = body.getByXPath(xpath).get(0);
                DomText domText = (DomText)o;
                System.out.println(domText.asText());
            }

        // 正文——比较特殊一点
        //经过确定，class="contson"这个类只被用来存储诗词内容，所以在获取信息时，
        //按照这个特征去找就OK。
            {
                String xpath = "//div[@class='cont']/div[@class='contson']";
                Object o = body.getByXPath(xpath).get(0);
                //因为这里正文不是一个DOM结点而是一个元素，所以转成HtmlElement
                HtmlElement element = (HtmlElement)o;
                System.out.println(element.getTextContent().trim());
            }
        }
    }

