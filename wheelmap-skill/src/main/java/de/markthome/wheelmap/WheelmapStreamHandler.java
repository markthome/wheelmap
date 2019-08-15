package de.markthome.wheelmap;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import de.markthome.wheelmap.handlers.AboutIntentHandler;
import de.markthome.wheelmap.handlers.ErrorHandler;
import de.markthome.wheelmap.handlers.FallbackIntentHandler;
import de.markthome.wheelmap.handlers.HelpIntentHandler;
import de.markthome.wheelmap.handlers.LaunchHandler;
import de.markthome.wheelmap.handlers.SessionEndedHandler;
import de.markthome.wheelmap.handlers.StopHandler;

public class WheelmapStreamHandler extends SkillStreamHandler {
	@SuppressWarnings("unchecked")
	private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new AboutIntentHandler(),
                        new FallbackIntentHandler(),
                        new StopHandler(),
                        new HelpIntentHandler(),
                        new LaunchHandler(),
                        new SessionEndedHandler(),
                        new ErrorHandler()
                )
                // Add your skill id below
                // .withSkillId("") // TODO
                .build();
    }

    public WheelmapStreamHandler() {
        super(getSkill());
    }
}
