package cn.com.testjquery.modle;

import java.util.Locale;

import javax.speech.Central;
import javax.speech.EngineList;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

public class HelloWorld
{
    
    public HelloWorld()
    {
    }
    
    public static void listAllVoices(String modeName)
    {
        System.out.println("All " + modeName + " Mode JSAPI Synthesizers and Voices:");
        
        SynthesizerModeDesc required = new SynthesizerModeDesc(null, modeName, Locale.US, null, null);
        
        EngineList engineList = Central.availableSynthesizers(required);
        for (int i = 0; i < engineList.size(); i++)
        {
            SynthesizerModeDesc desc = (SynthesizerModeDesc)engineList.get(i);
            System.out.println("    " + desc.getEngineName() + " (mode=" + desc.getModeName() + ", locale="
                + desc.getLocale() + "):");
            Voice voices[] = desc.getVoices();
            for (int j = 0; j < voices.length; j++)
            {
                System.out.println("        " + voices[j].getName());
            }
        }
    }
    
    public static void main(String args[])
    {
        // 利用 FreeTTS 读出Good job
        try
        {
            SynthesizerModeDesc desc = new SynthesizerModeDesc("FreeTTS en_US general synthesizer", "general",
                Locale.US, null, null);
            Synthesizer synthesizer = Central.createSynthesizer(desc);
            if (synthesizer == null)
            {
                System.exit(1);
            }
            synthesizer.allocate();
            synthesizer.resume();
            desc = (SynthesizerModeDesc)synthesizer.getEngineModeDesc();
            Voice voices[] = desc.getVoices();
            for (Voice v : voices)
            {
                synthesizer.getSynthesizerProperties().setVoice(v);
                synthesizer.speakPlainText("good job", null);
                synthesizer.waitEngineState(0x10000L);
            }
            synthesizer.deallocate();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}