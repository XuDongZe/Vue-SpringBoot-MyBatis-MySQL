export const getColorByDifficulty = (difficulty) => {
	const EASY_COLOR = "#87d068"
	const NORMAL_COLOR = "#f0ad4e"
	const HARD_COLOR = "#d9534f"
  let str = difficulty.toLowerCase()
  switch(str) {
    case "简单":
      return EASY_COLOR;
    case "中等":
      return NORMAL_COLOR;
    case "困难":
      return HARD_COLOR;
    default:
      return NORMAL_COLOR;
  }
};