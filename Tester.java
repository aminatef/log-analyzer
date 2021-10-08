import java.util.*;

public class Tester
{
    public void testlogEntry() {
        logEntry le = new logEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        logEntry le2 = new logEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    public void testLogAnalyzer() {
        logAnalyzer analyzer = new logAnalyzer();
        analyzer.readFile("short-test_log.txt");
        analyzer.printAll();
    }
    public void testUniqueIpAddress(){
        logAnalyzer analyzer = new logAnalyzer();
        analyzer.readFile("weblog2.txt");
        System.out.println("Number of unique IPs = " + analyzer.findUniqueIpAddress());
    }
    public void testPrintAllHigherThanNum(){
        logAnalyzer analyzer = new logAnalyzer();
        analyzer.readFile("webLongShortLog.txt");   
        analyzer.printAllHigherThanNum(400);
    }
    public void testIPVisits(){
        logAnalyzer analyzer= new logAnalyzer();
        analyzer.readFile("weblog1_log.txt");
        ArrayList<String> rec = analyzer.uniqueIPVisitsOnDay("Mar 24");
        for(String s : rec){
            System.out.println(s);
        }
        System.out.println(rec.size());
    }
    public void testIPsInRange(){
        logAnalyzer analyzer= new logAnalyzer();
        analyzer.readFile("weblog2.txt");
        int num = analyzer.countUniqueIPsInRange(200,299);
        System.out.println("count Unique IPs InRange = "+ num);
    }
    public void testCounts(){
        logAnalyzer analyzer = new logAnalyzer();
        analyzer.readFile("weblog2.txt");
        HashMap<String,Integer> map = analyzer.countVisitsPerIP();
        int max = analyzer.mostNumberVisitsByIP(map);
        System.out.println("MAX HERE"+ max);
        System.out.println("IP IS here" + analyzer.iPsMostVisits(map));
        System.out.println(map);
    }
    public void testdayWithMostIPVisits(){
        logAnalyzer analyzer = new logAnalyzer();
        analyzer.readFile("weblog2.txt");
        System.out.println(analyzer.dayWithMostIPVisits(analyzer.iPsForDays()));
    }
    public void testiPsWithMostVisitsOnDay(){
        logAnalyzer analyzer = new logAnalyzer();
        analyzer.readFile("weblog2.txt");
        HashMap<String,ArrayList<String>> map = analyzer.iPsForDays();
        System.out.println("MAP HERE"+map);
        System.out.println(analyzer.iPsWithMostVisitsOnDay(map,"sep 30"));
        
    }
}
