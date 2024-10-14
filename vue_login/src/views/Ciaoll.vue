<template>
  <div>
    <img class="center" src="../assets/ciallo.gif" alt="">
    <div>
      <marquee behavior="scroll" direction="left" scrollamount="30"><font color="red" size="3px" _msttexthash="37636469" _msthash="1">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="15"><font color="aqua" size="10px" _msttexthash="37636742" _msthash="2">Ciallo~（∠・ω&lt; ）⌒☆</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="25"><font _msttexthash="37636469" _msthash="3">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="12"><font color="coral" size="10px" _msttexthash="37636469" _msthash="4">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="28"><font color="red" _msttexthash="37636469" _msthash="5">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="20"><font color="greenyellow" size="13px" _msttexthash="37636742" _msthash="6">Ciallo~（∠・ω&lt; ）⌒☆</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="10"><font size="15px" _msttexthash="37636469" _msthash="7">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="15"><font color="pink" size="20px" _msttexthash="37636469" _msthash="8">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="14"><font color="aqua" size="6px" _msttexthash="37636742" _msthash="9">Ciallo~（∠・ω&lt; ）⌒☆</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="28"><font color="red" _msttexthash="37636742" _msthash="10">Ciallo~（∠・ω&lt; ）⌒☆</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="8"><font color="greenyellow" size="10px" _msttexthash="37636469" _msthash="11">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="20"><font color="pink" size="9px" _msttexthash="37636469" _msthash="12">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="13"><font color="green" size="5px" _msttexthash="37636742" _msthash="13">Ciallo~（∠・ω&lt; ）⌒☆</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="17"><font color="blue" size="5px" _msttexthash="37636742" _msthash="14">Ciallo~（∠・ω&lt; ）⌒☆</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="16"><font color="yellow" size="7px" _msttexthash="37636469" _msthash="15">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="11"><font color="red" size="5px" _msttexthash="37636469" _msthash="16">Ciallo~（∠・ω&lt; ）⌒ ★</font></marquee>
      <marquee behavior="scroll" direction="left" scrollamount="10"><font color="orange" size="8px" _msttexthash="37636742" _msthash="17">Ciallo~（∠・ω&lt; ）⌒☆</font></marquee>
    </div>
    <div class="footer">
      <p>该页面参照Ciallo网，仅供学习交流</p>
    </div>
  </div>
</template>

<script>
import $ from 'jquery';
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Ciaoll",
  data(){
    return {

    }
  },
  methods:{

  },
  mounted() {
    let a_idx = 0;
    let ac = new AudioContext();

    $(function (){
      $("body").click(function(e) {
        load_sound(require('../assets/Ciallo~（∠・ω- ）⌒ ★.aac'));
        const a = ["Ciallo～(∠・ω< )⌒★", "Ciallo～(∠・ω< )⌒☆"];
        const $i = $("<span/>").text(a[a_idx]);
        a_idx = (a_idx + 1) % a.length;
        const x = e.pageX
        const y = e.pageY;
        $i.css({
          "z-index": 99,
          "top": y - 20,
          "left": x,
          "position": "absolute",
          "font-weight": "bold",
          "color": color16()
        });
        $("body").append($i);
        $i.animate({
              "top": y - 180,
              "opacity": 0
            }, 1500,
            function() {
              $i.remove();
            });
      });
    })

    function color16(){
      const r = Math.floor(Math.random()*256);
      const g = Math.floor(Math.random()*256);
      const b = Math.floor(Math.random()*256);
      return `#${r.toString(16)}${g.toString(16)}${b.toString(16)}`;
    }

    function load_sound(url_data) {
      const req = new XMLHttpRequest();
      req.open('GET', url_data, true);
      req.responseType = 'arraybuffer';
      req.onload = function() {
        ac.decodeAudioData(req.response, function(buffer){
          const source = ac.createBufferSource();
          source.buffer = buffer;
          source.connect(ac.destination);
          source.start();
        },function (e) {
          console.info('错误' + e);
        });
      }
      req.send();
    }
  }
}
</script>

<style scoped>
.center {
  width: 540px;
  height: 120px;
  margin: auto;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
.footer {
  height: 5px;
  text-align: center;
  padding: 20px;
}
</style>