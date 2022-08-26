// Code to create a simple SuggestionProvider for commands in brigadier (minecraft forge 1.18.2)
// I'd recommend making a seperate class for everything above the dispatcher but for simplicity
// I put it this way now, let us start by creating an array with strings
        String[] texts = new String[10];
                texts[0] = "test1";
                texts[1] = "test2";
                texts[2] = "test3";
                texts[3] = "test4";
                texts[4] = "test5";
                texts[5] = "test6";
                //then we will define the provider this way
                SuggestionProvider<CommandSourceStack> provider = new SuggestionProvider<CommandSourceStack>() {
@Override
public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSourceStack> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        int I = 0;
        while (texts[I] != null) {
        builder.suggest(texts[I]); //we add the suggestions we defined in the array above right here in the suggestions provider
        I++;
        }
        return builder.buildFuture(); // here we return a CompletableFuture object our command can use
        }
        };

        //here in our dispatcher where we define the command, we can use a simple StringArgumentType.word() which in this case we call 'player', with .suggests we can now add our provider
        //all done, easy!
        dispatcher.register(Commands.literal("party").then(Commands.literal("add").then(Commands.argument("player", StringArgumentType.word()).suggests(provider).executes((command) -> {
        return run(command,StringArgumentType.getString(command, "player"));
        }))));
