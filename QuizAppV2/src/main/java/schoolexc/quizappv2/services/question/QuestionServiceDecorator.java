/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.services.question;

/**
 *
 * @author LE TUNG
 */
public abstract class QuestionServiceDecorator extends BaseQuestionService{
    protected BaseQuestionService decorator;

    public QuestionServiceDecorator(BaseQuestionService decorator) {
        this.decorator = decorator;
    }
    
    
}
