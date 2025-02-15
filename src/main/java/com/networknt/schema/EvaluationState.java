package com.networknt.schema;

public class EvaluationState {
    private final JsonNodePath evaluationPath;
    private final JsonSchema evaluationSchema;

    public EvaluationState(JsonNodePath evaluationPath, JsonSchema evaluationSchema) {
        this.evaluationPath = evaluationPath;
        this.evaluationSchema = evaluationSchema;
    }

    public JsonSchema getEvaluationSchema() {
        return evaluationSchema;
    }

    public JsonNodePath getEvaluationPath() {
        return evaluationPath;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EvaluationState [evaluationPath=");
        builder.append(evaluationPath);
        builder.append(", evaluationSchema=");
        builder.append(evaluationSchema);
        builder.append("]");
        return builder.toString();
    }
}
