export const BVS_ADDRRESS = '0xFC8EE7641AD22e2f9EB1ad86560DDC18f1877C03'
export const BVS_ABI = [
  {
    "inputs": [
      {
        "internalType": "string",
        "name": "_voter",
        "type": "string"
      },
      {
        "internalType": "uint256",
        "name": "_candidateId",
        "type": "uint256"
      },
      {
        "internalType": "uint256",
        "name": "_electionId",
        "type": "uint256"
      }
    ],
    "name": "insertVote",
    "outputs": [],
    "stateMutability": "nonpayable",
    "type": "function"
  },
  {
    "anonymous": false,
    "inputs": [
      {
        "components": [
          {
            "internalType": "string",
            "name": "voter",
            "type": "string"
          },
          {
            "internalType": "uint256",
            "name": "candidateId",
            "type": "uint256"
          },
          {
            "internalType": "uint256",
            "name": "electionId",
            "type": "uint256"
          }
        ],
        "indexed": false,
        "internalType": "struct VotingSystem.Vote",
        "name": "vote",
        "type": "tuple"
      }
    ],
    "name": "VoteInserted",
    "type": "event"
  },
  {
    "inputs": [
      {
        "internalType": "string",
        "name": "a",
        "type": "string"
      },
      {
        "internalType": "string",
        "name": "b",
        "type": "string"
      }
    ],
    "name": "compareString",
    "outputs": [
      {
        "internalType": "bool",
        "name": "",
        "type": "bool"
      }
    ],
    "stateMutability": "pure",
    "type": "function"
  },
  {
    "inputs": [
      {
        "internalType": "uint256",
        "name": "",
        "type": "uint256"
      }
    ],
    "name": "votes",
    "outputs": [
      {
        "internalType": "string",
        "name": "voter",
        "type": "string"
      },
      {
        "internalType": "uint256",
        "name": "candidateId",
        "type": "uint256"
      },
      {
        "internalType": "uint256",
        "name": "electionId",
        "type": "uint256"
      }
    ],
    "stateMutability": "view",
    "type": "function"
  }
]
