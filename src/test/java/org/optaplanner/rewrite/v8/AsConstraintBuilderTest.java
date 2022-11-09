package org.optaplanner.rewrite.v8;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

class AsConstraintBuilderTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new AsConstraintBuilder())
            .parser(JavaParser.fromJavaVersion()
                .classpath("optaplanner-core"));
    }

    // ************************************************************************
    // Uni
    // ************************************************************************

    @Test
    void uniPenalizeName() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(\"My constraint\", HardSoftScore.ONE_HARD);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(HardSoftScore.ONE_HARD)\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeId() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(\"My package\", \"My constraint\", HardSoftScore.ONE_HARD);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(HardSoftScore.ONE_HARD)\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableName() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable(\"My constraint\");"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable()\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableId() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable(\"My package\", \"My constraint\");"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable()\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    @Test
    void uniPenalizeNameMatchWeigherInt() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(\"My constraint\", HardSoftScore.ONE_HARD, (a) -> 7);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(HardSoftScore.ONE_HARD, (a) -> 7)\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeIdMatchWeigherInt() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(\"My package\", \"My constraint\", HardSoftScore.ONE_HARD, (a) -> 7);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalize(HardSoftScore.ONE_HARD, (a) -> 7)\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableNameMatchWeigherInt() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable(\"My constraint\", (a) -> 7);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable((a) -> 7)\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableIdMatchWeigherInt() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable(\"My package\", \"My constraint\", (a) -> 7);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurable((a) -> 7)\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    @Test
    void uniPenalizeNameMatchWeigherLong() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeLong(\"My constraint\", HardSoftLongScore.ONE_HARD, (a) -> 7L);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeLong(HardSoftLongScore.ONE_HARD, (a) -> 7L)\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeIdMatchWeigherLong() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeLong(\"My package\", \"My constraint\", HardSoftLongScore.ONE_HARD, (a) -> 7L);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeLong(HardSoftLongScore.ONE_HARD, (a) -> 7L)\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableNameMatchWeigherLong() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableLong(\"My constraint\", (a) -> 7L);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableLong((a) -> 7L)\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableIdMatchWeigherLong() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableLong(\"My package\", \"My constraint\", (a) -> 7L);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableLong((a) -> 7L)\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    @Test
    void uniPenalizeNameMatchWeigherBigDecimal() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeBigDecimal(\"My constraint\", HardSoftBigDecimalScore.ONE_HARD, (a) -> BigDecimal.TEN);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeBigDecimal(HardSoftBigDecimalScore.ONE_HARD, (a) -> BigDecimal.TEN)\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeIdMatchWeigherBigDecimal() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeBigDecimal(\"My package\", \"My constraint\", HardSoftBigDecimalScore.ONE_HARD, (a) -> BigDecimal.TEN);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeBigDecimal(HardSoftBigDecimalScore.ONE_HARD, (a) -> BigDecimal.TEN)\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableNameMatchWeigherBigDecimal() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableBigDecimal(\"My constraint\", (a) -> BigDecimal.TEN);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableBigDecimal((a) -> BigDecimal.TEN)\n" +
                "                .asConstraint(\"My constraint\");")));
    }

    @Test
    void uniPenalizeConfigurableIdMatchWeigherBigDecimal() {
        rewriteRun(java(
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableBigDecimal(\"My package\", \"My constraint\", (a) -> BigDecimal.TEN);"),
            wrap("        return f.forEach(String.class)\n" +
                "                .penalizeConfigurableBigDecimal((a) -> BigDecimal.TEN)\n" +
                "                .asConstraint(\"My package\", \"My constraint\");")));
    }

    // TODO reward, impact

    // ************************************************************************
    // Bi
    // ************************************************************************

    // ************************************************************************
    // Tri
    // ************************************************************************

    // ************************************************************************
    // Quad
    // ************************************************************************

    // ************************************************************************
    // Helper methods
    // ************************************************************************

    private static String wrap(String content) {
        return "import java.math.BigDecimal;\n" +
            "import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;\n" +
            "import org.optaplanner.core.api.score.buildin.hardsoftlong.HardSoftLongScore;\n" +
            "import org.optaplanner.core.api.score.buildin.hardsoftbigdecimal.HardSoftBigDecimalScore;\n" +
            "import org.optaplanner.core.api.score.stream.ConstraintFactory;\n" +
            "import org.optaplanner.core.api.score.stream.Constraint;\n" +
            "\n" +
            "class Test {\n" +
            "    Constraint myConstraint(ConstraintFactory f) {\n" +
            content + "\n" +
            "    }" +
            "}\n";
    }

}
