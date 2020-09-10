let userscore = 0;  //2 variables userscore and computer score
let computerscore = 0;

// getting id of each tag needed from the html file
const userscore_span = document.getElementById("user-score");
const computerscore_span = document.getElementById("computer-score");
const result_p = document.querySelector(".result > p");
const rock_div = document.getElementById("rock");
const paper_div = document.getElementById("paper");
const scissor_div = document.getElementById("scissors");



/**
 * Random choice of rock/paper/scissors generated as a computer players choice
 * @returns {string}
 */
function getcomputerchoice() {
    const choices = ["rock", "paper", "scissors"];
    // Math.floor is used for whole numbers Math.random is used to show random numbers between 0 and 1
    //It is multiplied by 3 to get a number between 0 and 2.
    const randomnumber = Math.floor(Math.random() * 3);
    //From the list of choices, we select one present in index number as randomnumber
    return choices[randomnumber]; // rock = 0 , paper =  1 , scissors = 2
}
/**
 * Check if the user wins according to their choices
 * @param userchoice
 * @param computerchoice
 */
function win(userchoice, computerchoice) {
    userscore_span.innerHTML = ++userscore;   // innerHTML overwrites the obtained html data
    result_p.innerHTML = `${(userchoice)}(user)  beats  ${(computerchoice)}(comp) . You won, yay !!`;
     result_p.innerHTML += ' <p style="font-size:50px">&#128512;</p> ' ;                                                                      // `` are used so there is no need for "" and + function

}

/**
 * Check if the user loses according to their choices
 * @param userchoice
 * @param computerchoice
 */
function lose(userchoice, computerchoice) {
    computerscore_span.innerHTML = ++computerscore;
    result_p.innerHTML = '';
    result_p.innerHTML = `${(computerchoice)}(comp)  beats  ${(userchoice)}(user) . You lost !!`;  // $ is used for the converting function
    result_p.innerHTML += ' <p style="font-size:50px">&#128542;</p> ' ;
}

/**
 * Check if the user and computer is in draw according to their choices
 * @param userchoice
 * @param computerchoice
 */
function draw(userchoice, computerchoice) {
    result_p.innerHTML = `${(computerchoice)}(comp)  ties  ${(userchoice)}(user) . its a draw!!`;
    result_p.innerHTML += ' <p style="font-size:50px">&#127958;</p> ' ;
}

/**
 * Function of game, which decides if the game is win/lose/draw
 * @param userchoice
 */
function game(userchoice) {
    const computerchoice = getcomputerchoice();

    // swith case used to identify the win, lose or  draw
    switch (userchoice + computerchoice) {
        case "rockscissors":
        case "paperrock":
        case "scissorspaper":
            win(userchoice, computerchoice);
            break;
        case "rockpaper":
        case "paperscissors":
        case "scissorsrock":
            lose(userchoice, computerchoice);
            break;
        case "rockrock":
        case "paperpaper":
        case "scissorsscissors":
            draw(userchoice, computerchoice);
            break;
    }
}


/**
 * Main function which is triggered once the script is called
 *
 */
function main() {
    //The event listener which gets triggered once the button of rock is clicked.
    rock_div.addEventListener('click', function () {
        console.log("rock");
        game("rock");
    })
    //The event listener which gets triggered once the button of paper is clicked.
    paper_div.addEventListener('click', function () {
        console.log("paper");
        game("paper");
    })
    //The event listener which gets triggered once the button of scissors is clicked.
    scissor_div.addEventListener('click', function () {
        console.log("scissor");
        game("scissors");
    })

}

/**
 * Calling main function
 */

main();