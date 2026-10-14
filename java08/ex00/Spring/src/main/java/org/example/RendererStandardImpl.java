package org.example;

public class RendererStandardImpl implements Renderer {

    private final PreProcessor preProcessor;

    public RendererStandardImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void render(String message) {
        String processedMessage = preProcessor.process(message);
        System.out.println(processedMessage);
    }
}
