pragma solidity >=0.4.22 <0.9.0;

import "./Migrations.sol";

contract VotingSystem is Migrations {
  
  struct Vote {
    string voter;
    uint candidateId;
    uint electionId;
  }

  event VoteInserted(Vote vote);

  Vote[] votes;

  //Vote count for a specific candidate
  mapping(uint => uint) public votesForCandidate;

  //Total votes in a specific election
  mapping(uint => uint) public votesInElection;

  function insertVote(string memory _voter, uint _candidateId, uint _electionId) public restricted {
    Vote memory vote = Vote(_voter, _candidateId, _candidateId);
    votes.push(vote);
    votesForCandidate[_candidateId]++;
    votesInElection[_electionId]++;
    emit VoteInserted(vote);
  }
}