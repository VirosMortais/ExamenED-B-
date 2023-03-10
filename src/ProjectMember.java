
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectMember extends Employee implements IProjectApp{
    private int memberNum=0;
    private String projectId;
    ArrayList<ProjectMember> memberList = new ArrayList<ProjectMember>();
    
    public ProjectMember() {
        super();
    }

    public ProjectMember(String projectId, int employeeId, String name, String sname, int age, String department, float salary) {
        super(employeeId, name, sname, age, department, salary);
        this.projectId = projectId;
    }

    public int getMemberNum() {
        return memberNum;
    }
    
    

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public String toString() {
        return "Project Member:" +  super.toString() + "\nProject ID: " + projectId + "\n\n\n";
    }

    public ArrayList<ProjectMember> getMemberList() {
        return memberList;
    }
    
    public String SearchMember(String searchid){
        
        try {
            return GetFromFile("FEmployee.txt", searchid);
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String AddMember(String searchid, String Info){
        try {
            return AddFile("FEmployee.txt", searchid, Info);
        } catch (IOException ex) {
            Logger.getLogger(ProjectMember.class.getName()).log(Level.SEVERE, null, ex);
            return "Employee could not be deleted!!!";
        }
    }
    
    public String UpdateMember(String fileName, String searchid, String Info) throws FileNotFoundException, IOException{
        
        //Yeni bir proje eklendi??inde projeye eklenen ??al????anlar??n bilgilerine proje id sini eklemek i??in kullan??l??r
        BufferedReader bReader2 = new BufferedReader(new FileReader(new File("FEmployee.txt")));
        String line2,str2="";
        String[] infos2;
        String message="Successfully added!!!" ;    
        
        while((line2 = bReader2.readLine()) != null){
            
        infos2=line2.split("\t");
        
        //bir projeye eklendi??i zaman o i??e al??nacak ??al????anlar??n hali haz??rda bir proje olup olmad??????n?? kontrol eder yoksa projeyi ekler
        if(infos2[6].equalsIgnoreCase("-") && searchid.equalsIgnoreCase(infos2[0])){
            infos2[6]=Info;
            line2=infos2[0]+"\t"+infos2[1]+"\t"+infos2[2]+"\t"+infos2[3]+"\t"+infos2[4]+"\t"+infos2[5]+"\t"+infos2[6]+"\t"+infos2[7]+"\n";
            str2+=line2;
        }else{
            str2+=line2+"\n";
        }
    }
    bReader2.close();
    BufferedWriter bWriter2 = new BufferedWriter(new FileWriter(new File("FEmployee.txt")));
    bWriter2.write(str2);
    bWriter2.close();
    
    return message;  
    }
    
    @Override
    public void ReadFile() throws FileNotFoundException, IOException{
        //??al????an dosyas??n?? okurmak ve arrayListe eklemek i??in kullan??l??r
        memberList.clear();
        int i=0;
        String[] infos ;
        String str="";
        BufferedReader bReader = new BufferedReader(new FileReader(new File("FEmployee.txt")));
        String line;
        
        //t??m dosyay?? okur ve ??al????an say??s??n?? tutar
        while((line = bReader.readLine()) != null){
            str+=line;
            infos=line.split("\t");
            
            ProjectMember member = new ProjectMember(infos[6], Integer.valueOf(infos[0]), infos[1], infos[2], Integer.valueOf(infos[3]), infos[4], Float.valueOf(infos[5]));
            memberList.add(member);
            
            i++;  
        }
        memberNum=i;
        bReader.close();
    }
}
