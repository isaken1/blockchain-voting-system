pragma solidity >=0.4.22 <0.9.0;

contract VotingSystem {
  
  struct Vote {
    string voter;
    uint candidateId;
    uint electionId;
  }

  event VoteInserted(Vote vote);

  Vote[] public votes;

  // //Vote count for a specific candidate
  // mapping(uint => uint) public numberOfVotesForCandidate;

  // //Total votes in a specific election
  // mapping(uint => uint) public numberOfVotesInElection;

  function insertVote(string memory _voter, uint _candidateId, uint _electionId) public {
    Vote memory vote = Vote(_voter, _candidateId, _electionId);
    votes.push(vote);
    // numberOfVotesForCandidate[_candidateId] = numberOfVotesForCandidate[_candidateId] + 1;
    // numberOfVotesInElection[_electionId] = numberOfVotesInElection[_electionId] + 1;
    emit VoteInserted(vote);
  }

  // function userHasVoted(string memory _voter, uint _electionId) public view returns(bool) {
  //   uint arrayLength = votes.length;
  //   for (uint i = 0; i < arrayLength; i++) {
  //     if ((votes[i].electionId == _electionId) && (compareString(_voter, votes[i].voter))){
  //       return true;
  //     }
  //   }
  //   return false;
  // }

  function compareString(string memory a, string memory b) public pure returns (bool) {
    return (keccak256(abi.encodePacked(a)) == keccak256(abi.encodePacked(b)));
  }
}