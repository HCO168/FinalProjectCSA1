import java.util.ArrayList;
import java.util.List;

public class NPC extends Person{
    private final ArrayList<String> questionAL=new ArrayList<>();
    private final ArrayList<String> answersAL=new ArrayList<>();
    public String defaultAnswer="";
    public NPC(String firstName, String lastName, String middleName, Gender gender, double age, double health, double dryWeight, double energy) {
        super(firstName, lastName, middleName, gender, age, health, dryWeight, energy);
    }
    public boolean setQuestionSet(ArrayList<String> questionAL,ArrayList<String> answersAL){
        if(questionAL.size()==answersAL.size()){
            this.questionAL.clear();
            this.answersAL.clear();
            this.questionAL.addAll(questionAL);
            this.answersAL.addAll(answersAL);
            return true;
        }else{
            return false;
        }
    }
    public boolean addQuestionSet(ArrayList<String> questionAL,ArrayList<String> answersAL){
        if(questionAL.size()==answersAL.size()){
            this.questionAL.addAll(questionAL);
            this.answersAL.addAll(answersAL);
            return true;
        }else{
            return false;
        }
    }
    public void addQuestionPair(String question,String answer){
        this.questionAL.add(question);
        this.answersAL.add(answer);
    }
    public boolean removeQuestionPair(int i){
        if(i>=this.questionAL.size()){
            return false;
        }
        this.questionAL.remove(i);
        this.answersAL.remove(i);
        return true;
    }
    public String answer(String question){
        for(int i=0;i<questionAL.size();i++){
            if(question.equalsIgnoreCase(questionAL.get(i))){
                return answersAL.get(i);
            }
        }
        return defaultAnswer;
    }
    private ArrayList<String> getQuestions(){
        return new ArrayList<>(questionAL);
    }
    private ArrayList<String> getAnswersAL(){
        return new ArrayList<>(answersAL);
    }
}
