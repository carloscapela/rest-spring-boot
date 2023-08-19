package br.com.restspring.api.getway.controllers;

import br.com.restspring.api.getway.converters.NumberConverter;
import br.com.restspring.api.getway.exceptions.UnsupportedMathOperationException;
import br.com.restspring.api.getway.math.SimpleMath;
import org.springframework.web.bind.annotation.*;

//import static org.apache.tomcat.util.http.parser.HttpParser.isNumeric;

@RestController
public class MathController {

    private final
    SimpleMath math = new SimpleMath();

    @RequestMapping(
            value = "/sum/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double sum (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        if (!NumberConverter.isDoubleNumeric(numberOne, numberTwo)) return 0D;

        return math.sum(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/sub/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double sub (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        if (!NumberConverter.isDoubleNumeric(numberOne, numberTwo)) return 0D;

        return math.sub(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/mult/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double mult (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        if (!NumberConverter.isDoubleNumeric(numberOne, numberTwo)) return 0D;

        return math.mult(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/div/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double div (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        if (!NumberConverter.isDoubleNumeric(numberOne, numberTwo)) return 0D;

        return math.div(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/med/{numberOne}/{numberTwo}",
            method = RequestMethod.GET
    )
    public Double med (
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        if (!NumberConverter.isDoubleNumeric(numberOne, numberTwo)) return 0D;

        return math.med(NumberConverter.converToDouble(numberOne), NumberConverter.converToDouble(numberTwo));
    }

    @RequestMapping(
            value = "/sqrt/{num}",
            method = RequestMethod.GET
    )
    public Double sqrt (@PathVariable(value = "num") String num){
        if (!NumberConverter.isNumeric(num)) {
            try {
                throw new UnsupportedMathOperationException("Por favor set um valor numerico.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return math.sqrt(NumberConverter.converToDouble(num));
    }


}
