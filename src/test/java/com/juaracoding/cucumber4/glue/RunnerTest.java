package com.juaracoding.cucumber4.glue;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"json:target/cucumber/cucumber.json"},
		features = {"src/main/resources/features"}
		)
public class RunnerTest {

}
