import java.util.*;
import edu.duke.*;
public class logAnalyzer {
    private ArrayList<logEntry> record;
    public logAnalyzer(){
        record  = new ArrayList<logEntry>();
    }
    public void readFile(String fileName){
        FileResource fr = new FileResource(fileName);
        WebLogParser pr = new WebLogParser();
        for(String line :fr.lines()){
            logEntry entry = pr.parseEntry(line);
            record.add(entry);
        }
        
    }
    public int findUniqueIpAddress(){
        ArrayList<String> copy = new ArrayList<String>();
        for(logEntry log : record){
            if(!(copy.contains(log.getipAddress()))){
                copy.add(log.getipAddress());
            }
        }
        return copy.size();
    }
    public void printAllHigherThanNum (int num){
        for(logEntry log : record){
            int status = log.getStatusCode();
            if(status > num){
                System.out.println(log);
            }
        }
    }
    public ArrayList<String> uniqueIPVisitsOnDay(String date){
        date = date.toLowerCase();
        ArrayList<String> IPsVisits = new ArrayList();
        ArrayList<String> copy = new ArrayList();
        for(logEntry log:record){
            String log_date = log.getAccessTime().toString();
            String comp = log_date.substring(4,11);
            comp = comp.toLowerCase();
            if(comp.contains(date)){
                IPsVisits.add(log.getipAddress());
            }
        }
        for(String IP: IPsVisits){
            if(! copy.contains(IP)){
                copy.add(IP);
            }
        }
        System.out.println("size " + copy.size());
        return copy;
    }
    public int countUniqueIPsInRange(int low ,int high){
        ArrayList<String> IPsInRange = new ArrayList<String>();
        ArrayList<String> copy = new ArrayList();
        for(logEntry log:record){
            int status = log.getStatusCode();
            if(status >=low && status<=high){
                IPsInRange.add(log.getipAddress());
            }
        }
        for(String IP: IPsInRange){
            if(! copy.contains(IP)){
                copy.add(IP);
            }
        }
        return copy.size();
    }
    public HashMap<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        for(logEntry log :record){
            String IP = log.getipAddress();
            if(counts.containsKey(IP)){
                int num = counts.get(IP);
                counts.put(IP,num+1);
            }
            else{
                counts.put(IP,1);
            }
        }
        return counts;
    }
    public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
        int max = 0;
        for(String IP : counts.keySet()){
            if(counts.get(IP)>max){
                max = counts.get(IP);
            }
        }
        return max;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
        int max = mostNumberVisitsByIP(counts);
        ArrayList<String> IPsMax = new ArrayList<String>();
        for(String IP :counts.keySet()){
            if(max == counts.get(IP)){
                IPsMax.add(IP);
            }
        }
        return IPsMax;
    }
    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        for(logEntry log : record){
            String log_date = log.getAccessTime().toString();
            String comp = log_date.substring(4,11);
            comp = comp.toLowerCase();
            if(map.containsKey(comp)){
                map.get(comp).add(log.getipAddress());
            }
            else{
                ArrayList<String> Ips = new ArrayList<String>();
                Ips.add(log.getipAddress());
                map.put(comp,Ips);  
            }
        }
        System.out.println(map);
        return map;
    }
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map){
        String ans = "";
        int max = 0;
        for(String day:map.keySet()){
            if(map.get(day).size() > max){
                max = map.get(day).size();
                ans = day;
            }
        }
        return ans;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map,String day){
         ArrayList<String> IPs = map.get(day.toLowerCase()+" ");
         System.out.println("IPS HERE" + IPs+map);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(String IP:IPs){
             if(counts.containsKey(IP)){
                 counts.put(IP,counts.get(IP)+1);
             }
             else{
                 counts.put(IP,1);
             }
         }
         return iPsMostVisits(counts);
    }
    public void printAll(){
        for(logEntry log : record){
            System.out.println(log);
        }
    }
}
