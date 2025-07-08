/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.serivces.question;

/**
 *
 * @author admin
 */
public abstract class QuestionServiceDecorator implements BaseQuestionService{

    protected BaseQuestionService decorator;

    public QuestionServiceDecorator(BaseQuestionService decorator) {
        this.decorator = decorator;
    }
    
    

}
