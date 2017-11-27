<template>
  <v-container>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3>
        <v-card>
          <v-card-title>
            <div>Chat with </div>
            <v-chip disabled>
              <v-avatar>
                <img src="https://randomuser.me/api/portraits/men/35.jpg" alt="Chat with">
              </v-avatar>
              Imie i nazwisko
            </v-chip>
          </v-card-title>
          <v-divider></v-divider>
          <v-card-text>
            <v-container
              style="max-height: 400px"
              class="scroll-y">
              <v-layout row>
                <v-flex>
                  <div>
                    <v-chip disabled small>
                      sadas
                    </v-chip>
                  </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div>
                  <div>
                    <v-chip disabled small>
                      sadas
                    </v-chip>
                  </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div>
                  <div>
                    <v-chip disabled small>
                      sadas
                    </v-chip>
                  </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div>
                  <div>
                    <v-chip disabled small>
                      sadas
                    </v-chip>
                  </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div><div>
                  <v-chip disabled small>
                    sadas
                  </v-chip>
                </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div>
                  <div>
                    <v-chip disabled small>
                      sadas
                    </v-chip>
                  </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div>
                  <div>
                    <v-chip disabled small>
                      sadas
                    </v-chip>
                  </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div>
                  <div>
                    <v-chip disabled small>
                      sadas
                    </v-chip>
                  </div>
                  <div style="text-align: right;">
                    <v-chip disabled small>
                      dsadsaa
                    </v-chip>
                  </div>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-text-field
              v-model="message"
              label="Enter message"
              multi-line
              rows="1"
              auto-grow
              elevation-22>
            </v-text-field>
          </v-card-actions>
        </v-card>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3>
        <!--<div class="chat-container">-->
        <!--<message :messages="messages" @imageLoad="scrollToEnd"></message>-->
        <!--</div>-->
        <!--<emoji-picker :show="emojiPanel" @close="toggleEmojiPanel" @click="addMessage"></emoji-picker>-->
        <!--<div class="typer">-->
        <!--<input type="text" placeholder="Enter message" v-on:keyup.enter="sendMessage" v-model="content" elevation-22>-->
        <!--</div>-->
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    data () {
      return {
        message: null
      }
    }
  }
</script>
