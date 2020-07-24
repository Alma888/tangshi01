package lab;
//测试HtmlUnit
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.File;
import java.io.IOException;
import java.util.List;

//列表页下载提取Demo
public class 列表页下载提取Demo {
    public static void main(String[] args) throws IOException {
        // HtmlUnit是个无界面的浏览器(HTTP 客户端)
        //用到它的一个WebClient类，也就是一个浏览器类，
        // (下面模拟一个chrome浏览器，其中构造方法的参数指定浏览器版本为CHROME这个版本)
        WebClient webClient = new WebClient(BrowserVersion.CHROME);//创建了一个浏览器类对象，指定
        // Options是选项的意思。
        // 关闭了浏览器的 js 执行引擎，不再执行网页中的 js 脚本
        webClient.getOptions().setJavaScriptEnabled(false);
        // 关闭了浏览器的 css 执行引擎，不再执行a网页中的 css 布局
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
        String url="https://so.gushiwen.org/gushi/tangshi.aspx";
        //对列表页面进行请求getPage,里面传的是一个具体网站的url,返回的是一个列表页
        HtmlPage page = webClient.getPage(url);
        System.out.println(page);

        File file = new File("唐诗三百首\\列表页.html");
        file.delete();
        page.save(file);//将抓到的页面保存在"唐诗三百首\\列表页.html"file文件中

        // 如何从 html 中提取我们需要的信息
        HtmlElement body = page.getBody();

        //得到一组element
        List<HtmlElement> elements = body.getElementsByAttribute(
                "div",//元素名称
                "class",//标签名称
                "typecont");//标签的值

        /*
        for (HtmlElement element : elements) {
            System.out.println(element);
        }
        */

        int count = 0;
        for (HtmlElement element : elements) {
            List<HtmlElement> aElements = element.getElementsByTagName("a");
            for (HtmlElement a : aElements) {
                System.out.println(a.getAttribute("href"));
                count++;
            }
        }
        System.out.println(count);
    }
}
