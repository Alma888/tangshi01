package lab;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.NlpAnalysis;

import java.util.List;

public class 分词Demo {
    public static void main(String[] args) {
        String sentence="中华人民共和国成立了！中国人民从此站起来了！";
      // List<Term> termList=NlpAnalysis.parse(title).getTerms();
      //自然语言识别 NLP（Nature Language Process）
        //分词只有一个方法 NlpAnalysis,后面以getTerms()方法得出来的就是一个单词。
        List<Term> termList=NlpAnalysis.parse(sentence).getTerms();
        for(Term term:termList){
            System.out.println(term.getNatureStr()+":"+term.getRealName());
        }
    }
}
