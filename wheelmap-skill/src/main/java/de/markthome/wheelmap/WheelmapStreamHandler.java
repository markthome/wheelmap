package de.markthome.wheelmap;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import de.markthome.wheelmap.handlers.AboutIntentHandler;
import de.markthome.wheelmap.handlers.AccessibilityHandler;
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
                        new AccessibilityHandler(),
                        new ErrorHandler()
                )
                .withSkillId("amzn1.ask.skill.d629a705-0afd-4e02-b044-f3dc4a5d6031")
                .build();
    }

    public WheelmapStreamHandler() {
        super(getSkill());
    }
}
