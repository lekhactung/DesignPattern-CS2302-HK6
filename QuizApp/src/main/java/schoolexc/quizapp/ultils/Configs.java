/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.ultils;

import schoolexc.quizapp.serivces.CategoryServices;
import schoolexc.quizapp.serivces.LevelServices;
import schoolexc.quizapp.serivces.question.QuestionService;
import schoolexc.quizapp.serivces.UpdateQuestionService;
import schoolexc.quizapp.serivces.question.BaseQuestionService;

/**
 *
 * @author admin
 */
public class Configs {

    public static final CategoryServices cateService = new CategoryServices();
    public static final LevelServices levelService = new LevelServices();
    public static final BaseQuestionService questionService = new QuestionService();
    public static final UpdateQuestionService uQuestionService = new UpdateQuestionService();

}
