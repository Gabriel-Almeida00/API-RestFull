package com.gabriel.api.controller;

import com.gabriel.api.converter.NumberConverters;
import com.gabriel.api.exceptions.UnsupportedMathOperationException;
import com.gabriel.api.math.SimpleMath;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private final AtomicLong counter = new AtomicLong();
    private SimpleMath simpleMath = new SimpleMath();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverters.isNumeric(numberOne) || !NumberConverters.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.sum(NumberConverters.convertToDouble(numberOne), NumberConverters.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sub(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverters.isNumeric(numberOne) || !NumberConverters.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.sub(NumberConverters.convertToDouble(numberOne), NumberConverters.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mult(@PathVariable(value = "numberOne") String numberOne,
                       @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverters.isNumeric(numberOne) || !NumberConverters.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.mult(NumberConverters.convertToDouble(numberOne), NumberConverters.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double div(@PathVariable(value = "numberOne") String numberOne,
                      @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverters.isNumeric(numberOne) || !NumberConverters.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.div(NumberConverters.convertToDouble(numberOne), NumberConverters.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double media(@PathVariable(value = "numberOne") String numberOne,
                        @PathVariable(value = "numberTwo") String numberTwo
    ) throws Exception {

        if (!NumberConverters.isNumeric(numberOne) || !NumberConverters.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.media(NumberConverters.convertToDouble(numberOne), NumberConverters.convertToDouble(numberTwo)) / 2;
    }

    @RequestMapping(value = "/raiz/{numberOne}", method = RequestMethod.GET)
    public Double raiz(@PathVariable(value = "numberOne") String numberOne
    ) throws Exception {

        if (!NumberConverters.isNumeric(numberOne)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return simpleMath.raiz(NumberConverters.convertToDouble(numberOne));
    }
}
