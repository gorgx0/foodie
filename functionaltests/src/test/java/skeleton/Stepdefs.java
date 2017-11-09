package skeleton;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {

    @Given("^a case$")
    public void aCase() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The case");
    }

    @When("^operation performed$")
    public void operationPerformed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("operation performed");
    }

    @Then("^result obtained$")
    public void resultObtained() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("result obtained");
    }

}


