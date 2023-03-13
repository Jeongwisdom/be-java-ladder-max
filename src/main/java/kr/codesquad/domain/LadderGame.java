package kr.codesquad.domain;

import kr.codesquad.view.Input;
import kr.codesquad.view.Output;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class LadderGame {

    private ArrayList<String> names;
    private int countOfLadder;
    public void startLadderGame() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Input input = new Input();
        Validator validator = new Validator();
        boolean checkName = true, checkLadder = true;

        while(checkName) {
            String answer = input.input(br, 0);
            String[] arrayNames = splitNames(answer);
            checkName = checkNames(validator, arrayNames);
        }

        while(checkLadder) {
            String answer = input.input(br, 1);
            checkLadder = checkLadder(validator, input, answer);
        }

        Ladder ladder = new Ladder(names.size(), countOfLadder);
        Output output = new Output();
        output.print(names, ladder);
    }

    private boolean checkNames(Validator validator, String[] arrayNames) {
        if(validator.validateNames(arrayNames)) {
            names = new ArrayList<>(Arrays.asList(arrayNames));
            return false;
        }
        return true;
    }

    private boolean checkLadder(Validator validator, Input input, String answer) {
        if(validator.validateLadder(answer)) {
            countOfLadder = input.inputLadder(answer);
            return false;
        }
        return true;
    }

    private String[] splitNames(String answer) {
        return answer.replaceAll(" ", "").split(",");
    }
}
