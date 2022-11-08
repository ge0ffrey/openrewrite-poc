package org.optaplanner.rewrite.v8;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

class CustomShadowVariableToShadowVariableTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new CustomShadowVariableToShadowVariable());
    }

    @Test
    @Disabled
    void todo() {
        rewriteRun(
            java("TODO",
                "TODO"));
    }

}
